package eu.zunix.tge_core.worldgen;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;

public class TGEOreGenerationEvents {
    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }

        ChunkAccess chunk = event.getChunk();
        TGEOreGenerator.scrubForeignOres(level, chunk);

        // Generate veins for any chunk that does not have recorded generated vein data yet.
        // This also allows retroactive generation in already existing worlds.
        if (OreChunkMetadata.get(level).getVeinInfo(chunk.getPos()) != null) {
            return;
        }
        TGEOreGenerator.generateChunkSliceForSector(level, chunk);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("tgeveins")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("list")
                                .then(Commands.argument("radius", IntegerArgumentType.integer(16, 1024))
                                        .executes(this::executeList))));
    }

    private int executeList(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerLevel level = source.getLevel();

        int radius = IntegerArgumentType.getInteger(context, "radius");
        int radiusChunks = Math.max(1, radius / 16);

        BlockPos origin = BlockPos.containing(source.getPosition());
        var list = TGEOreGenerator.listVeins(level, origin, radiusChunks);
        if (list.isEmpty()) {
            source.sendSuccess(() -> Component.literal("No veins found in range."), false);
            return 1;
        }

        for (TGEOreGenerator.SectorDebugInfo info : list) {
            String line = "[" + info.x() + ", " + info.z() + "] | Name: " + info.veinName() + " | Y: " + info.centerY();
            source.sendSuccess(() -> Component.literal(line), false);
        }

        return list.size();
    }
}
