package eu.zunix.tge_core.registry;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.item.PeriodicTooltipBlockItem;
import eu.zunix.tge_core.content.item.PeriodicTooltipItem;
import eu.zunix.tge_core.content.material.OreMaterial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TGEItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TGECore.MODID);

    private static final Map<OreMaterial, OreSetRegistration> ORE_SETS = new EnumMap<>(OreMaterial.class);
    private static final List<DeferredItem<? extends Item>> DISPLAY_ORDER = new ArrayList<>();

    static {
        for (OreMaterial material : OreMaterial.values()) {
            OreSetRegistration set = registerOreSet(material);
            ORE_SETS.put(material, set);
            DISPLAY_ORDER.addAll(set.displayOrder());
        }
    }

    private TGEItems() {
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    public static OreSetRegistration oreSet(OreMaterial material) {
        return ORE_SETS.get(material);
    }

    public static List<DeferredItem<? extends Item>> displayItems() {
        return DISPLAY_ORDER;
    }

    private static OreSetRegistration registerOreSet(OreMaterial material) {
        DeferredItem<BlockItem> ore = ITEMS.register(material.oreId(),
                () -> new PeriodicTooltipBlockItem(TGEBlocks.ore(material).get(), material.formula(), new Item.Properties()));

        DeferredItem<BlockItem> deepslateOre = ITEMS.register(material.deepslateOreId(),
                () -> new PeriodicTooltipBlockItem(TGEBlocks.deepslateOre(material).get(), material.formula(), new Item.Properties()));

        DeferredItem<Item> rawMaterial = registerMaterialItem(material.rawMaterialId(), material.formula());
        DeferredItem<Item> ingot = registerMaterialItem(material.ingotId(), material.formula());
        DeferredItem<Item> dust = registerMaterialItem(material.dustId(), material.formula());
        DeferredItem<Item> dirtyDust = registerMaterialItem(material.dirtyDustId(), material.formula());
        DeferredItem<Item> rod = registerMaterialItem(material.rodId(), material.formula());
        DeferredItem<Item> plate = registerMaterialItem(material.plateId(), material.formula());
        DeferredItem<Item> gear = registerMaterialItem(material.gearId(), material.formula());
        DeferredItem<Item> bolt = registerMaterialItem(material.boltId(), material.formula());
        DeferredItem<Item> screw = registerMaterialItem(material.screwId(), material.formula());
        DeferredItem<Item> nugget = registerMaterialItem(material.nuggetId(), material.formula());
        DeferredItem<Item> longRod = registerMaterialItem(material.longRodId(), material.formula());

        return new OreSetRegistration(
                ore,
                deepslateOre,
                rawMaterial,
                ingot,
                dust,
                dirtyDust,
                rod,
                plate,
                gear,
                bolt,
                screw,
                nugget,
                longRod);
    }

    private static DeferredItem<Item> registerMaterialItem(String id, String formula) {
        return ITEMS.register(id, () -> new PeriodicTooltipItem(formula, new Item.Properties()));
    }

    public record OreSetRegistration(
            DeferredItem<BlockItem> ore,
            DeferredItem<BlockItem> deepslateOre,
            DeferredItem<Item> rawMaterial,
            DeferredItem<Item> ingot,
            DeferredItem<Item> dust,
            DeferredItem<Item> dirtyDust,
            DeferredItem<Item> rod,
            DeferredItem<Item> plate,
            DeferredItem<Item> gear,
            DeferredItem<Item> bolt,
            DeferredItem<Item> screw,
            DeferredItem<Item> nugget,
            DeferredItem<Item> longRod) {

        public List<DeferredItem<? extends Item>> displayOrder() {
            return List.of(
                    ore,
                    deepslateOre,
                    rawMaterial,
                    ingot,
                    dust,
                    dirtyDust,
                    rod,
                    plate,
                    gear,
                    bolt,
                    screw,
                    nugget,
                    longRod);
        }
    }
}
