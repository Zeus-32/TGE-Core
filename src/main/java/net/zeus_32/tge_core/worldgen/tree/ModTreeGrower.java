package net.zeus_32.tge_core.worldgen.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.zeus_32.tge_core.TGECore;
import net.zeus_32.tge_core.worldgen.ModConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrower {
    public static final TreeGrower RUBBER_TREE = new TreeGrower(TGECore.MODID + ":rubber",
            Optional.empty(), Optional.of(ModConfiguredFeatures.RUBBER_KEY), Optional.empty());
}
