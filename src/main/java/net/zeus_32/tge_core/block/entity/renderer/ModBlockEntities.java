package net.zeus_32.tge_core.block.entity.renderer;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.block.ModBlocks;
import net.zeus_32.tge_core.block.entity.CokeOvenBlockEntity;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, TGECore.MODID);

    public static final Supplier<BlockEntityType<CokeOvenBlockEntity>> COKE_OVEN_BE =
            BLOCK_ENTITIES.register("coke_oven_be", () -> BlockEntityType.Builder.of(
                    CokeOvenBlockEntity::new, ModBlocks.COKE_OVEN.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
