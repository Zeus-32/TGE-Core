package eu.zunix.tge_core.registry;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import eu.zunix.tge_core.TGECore;
import eu.zunix.tge_core.content.item.CreativeProspectorItem;
import eu.zunix.tge_core.content.item.ProspectorItem;
import eu.zunix.tge_core.content.item.PeriodicTooltipBlockItem;
import eu.zunix.tge_core.content.item.PeriodicTooltipItem;
import eu.zunix.tge_core.content.item.TGEMachineBlockItem;
import eu.zunix.tge_core.content.kinetic.TGEMachineTier;
import eu.zunix.tge_core.content.kinetic.TGEMachineType;
import eu.zunix.tge_core.content.material.OreMaterial;
import eu.zunix.tge_core.content.material.TGEMaterials;
import eu.zunix.tge_core.registry.TGEBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TGEItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TGECore.MODID);

    private static final Map<OreMaterial, OreSetRegistration> ORE_SETS = new EnumMap<>(OreMaterial.class);
    private static final Map<TGEMachineTier, MachineSetRegistration> MACHINE_SETS = new EnumMap<>(TGEMachineTier.class);

    private static final List<DeferredItem<? extends Item>> MATERIALS_DISPLAY_ORDER = new ArrayList<>();
    private static final List<DeferredItem<? extends Item>> BLOCKS_DISPLAY_ORDER = new ArrayList<>();
    private static final List<DeferredItem<? extends Item>> TOOLS_DISPLAY_ORDER = new ArrayList<>();
    private static final List<DeferredItem<? extends Item>> MACHINES_DISPLAY_ORDER = new ArrayList<>();

    public static final DeferredItem<Item> PROSPECTOR =
            ITEMS.register("prospector", () -> new ProspectorItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> CREATIVE_PROSPECTOR =
            ITEMS.register("creative_prospector", () -> new CreativeProspectorItem(new Item.Properties().stacksTo(1)));

    static {
        for (OreMaterial material : TGEMaterials.ACTIVE) {
            registerMaterialSet(material);
        }

        TOOLS_DISPLAY_ORDER.add(PROSPECTOR);
        TOOLS_DISPLAY_ORDER.add(CREATIVE_PROSPECTOR);

        for (TGEMachineTier tier : TGEMachineTier.values()) {
            MachineSetRegistration set = registerMachineSet(tier);
            MACHINE_SETS.put(tier, set);
            MACHINES_DISPLAY_ORDER.addAll(set.displayOrder());
        }
    }

    private static void registerMaterialSet(OreMaterial material) {
        OreSetRegistration set = registerOreSet(material);
        ORE_SETS.put(material, set);
        BLOCKS_DISPLAY_ORDER.addAll(set.blockDisplayOrder());
        MATERIALS_DISPLAY_ORDER.addAll(set.materialDisplayOrder());
    }

    private TGEItems() {
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    public static OreSetRegistration oreSet(OreMaterial material) { return ORE_SETS.get(material); }
    public static List<DeferredItem<? extends Item>> materialItems() { return MATERIALS_DISPLAY_ORDER; }
    public static List<DeferredItem<? extends Item>> blockItems() { return BLOCKS_DISPLAY_ORDER; }
    public static List<DeferredItem<? extends Item>> toolItems() { return TOOLS_DISPLAY_ORDER; }
    public static List<DeferredItem<? extends Item>> machineItems() { return MACHINES_DISPLAY_ORDER; }
    public static MachineSetRegistration machineSet(TGEMachineTier tier) { return MACHINE_SETS.get(tier); }

    private static OreSetRegistration registerOreSet(OreMaterial material) {
        DeferredItem<BlockItem> ore = null;
        DeferredItem<BlockItem> deepslateOre = null;
        DeferredItem<BlockItem> netherOre = null;
        DeferredItem<BlockItem> endOre = null;
        DeferredItem<Item> rawMaterial = null;
        DeferredItem<Item> ingot = null;
        DeferredItem<Item> dust = null;
        DeferredItem<Item> dirtyDust = null;
        DeferredItem<Item> purifiedDust = null;
        DeferredItem<Item> rod = null;
        DeferredItem<Item> plate = null;
        DeferredItem<Item> doublePlate = null;
        DeferredItem<Item> ring = null;
        DeferredItem<Item> gear = null;
        DeferredItem<Item> spring = null;
        DeferredItem<Item> bolt = null;
        DeferredItem<Item> screw = null;
        DeferredItem<Item> nugget = null;
        DeferredItem<Item> longRod = null;
        DeferredItem<Item> flawlessGem = null;
        DeferredItem<Item> exquisiteGem = null;

        if (material.hasOreForm() && TGEBlocks.hasOre(material)) {
            ore = ITEMS.register(material.oreId(),
                    () -> new PeriodicTooltipBlockItem(TGEBlocks.ore(material).get(), material.formula(), new Item.Properties()));
            deepslateOre = ITEMS.register(material.deepslateOreId(),
                    () -> new PeriodicTooltipBlockItem(TGEBlocks.deepslateOre(material).get(), material.formula(), new Item.Properties()));
            netherOre = ITEMS.register(material.netherOreId(),
                    () -> new PeriodicTooltipBlockItem(TGEBlocks.netherOre(material).get(), material.formula(), new Item.Properties()));
            endOre = ITEMS.register(material.endOreId(),
                    () -> new PeriodicTooltipBlockItem(TGEBlocks.endOre(material).get(), material.formula(), new Item.Properties()));
        }

        if (material.hasRawMaterialForm()) {
            rawMaterial = registerMaterialItem(material.rawMaterialId(), material.formula());
        }

        if (material.hasIngotForm()) {
            ingot = registerMaterialItem(material.ingotId(), material.formula());
        }

        if (material.hasGemForm()) {
            flawlessGem = registerMaterialItem(material.flawlessGemId(), material.formula());
            exquisiteGem = registerMaterialItem(material.exquisiteGemId(), material.formula());
        }

        if (material.hasDustForm()) {
            dust = registerMaterialItem(material.dustId(), material.formula());
        }
        if (material.hasDirtyDustForm()) {
            dirtyDust = registerMaterialItem(material.dirtyDustId(), material.formula());
        }
        if (material.hasPurifiedDustForm()) {
            purifiedDust = registerMaterialItem(material.purifiedDustId(), material.formula());
        }
        if (material.hasRodForm()) {
            rod = registerMaterialItem(material.rodId(), material.formula());
        }
        if (material.hasPlateForm()) {
            plate = registerMaterialItem(material.plateId(), material.formula());
        }
        if (material.hasDoublePlateForm()) {
            doublePlate = registerMaterialItem(material.doublePlateId(), material.formula());
        }
        if (material.hasRingForm()) {
            ring = registerMaterialItem(material.ringId(), material.formula());
        }
        if (material.hasGearForm()) {
            gear = registerMaterialItem(material.gearId(), material.formula());
        }
        if (material.hasSpringForm()) {
            spring = registerMaterialItem(material.springId(), material.formula());
        }
        if (material.hasBoltForm()) {
            bolt = registerMaterialItem(material.boltId(), material.formula());
        }
        if (material.hasScrewForm()) {
            screw = registerMaterialItem(material.screwId(), material.formula());
        }
        if (material.hasNuggetForm()) {
            nugget = registerMaterialItem(material.nuggetId(), material.formula());
        }
        if (material.hasLongRodForm()) {
            longRod = registerMaterialItem(material.longRodId(), material.formula());
        }

        return new OreSetRegistration(
                ore, deepslateOre, netherOre, endOre,
                rawMaterial, ingot, dust, dirtyDust, purifiedDust,
                rod, plate, doublePlate, ring, gear, spring, bolt, screw, nugget, longRod,
                flawlessGem, exquisiteGem);
    }

    private static DeferredItem<Item> registerMaterialItem(String id, String formula) {
        TGECore.LOGGER.info("TGE: registering material item id: {}", id);
        return ITEMS.register(id, () -> new PeriodicTooltipItem(formula, new Item.Properties()));
    }

    private static MachineSetRegistration registerMachineSet(TGEMachineTier tier) {
        Map<TGEMachineType, DeferredItem<Item>> items = new EnumMap<>(TGEMachineType.class);
        for (TGEMachineType type : TGEMachineType.values()) {
            String id = tier.id() + "_" + type.id();
            TGECore.LOGGER.info("TGE: registering machine item id: {}", id);
            items.put(type, ITEMS.register(id, () -> new TGEMachineBlockItem(TGEBlocks.machine(tier, type).get(), new Item.Properties())));
        }
        return new MachineSetRegistration(Map.copyOf(items));
    }

    public record OreSetRegistration(
            DeferredItem<BlockItem> ore, DeferredItem<BlockItem> deepslateOre, DeferredItem<BlockItem> netherOre, DeferredItem<BlockItem> endOre,
            DeferredItem<Item> rawMaterial, DeferredItem<Item> ingot, DeferredItem<Item> dust, DeferredItem<Item> dirtyDust, DeferredItem<Item> purifiedDust,
            DeferredItem<Item> rod, DeferredItem<Item> plate, DeferredItem<Item> doublePlate, DeferredItem<Item> ring, DeferredItem<Item> gear, DeferredItem<Item> spring, DeferredItem<Item> bolt, DeferredItem<Item> screw, DeferredItem<Item> nugget, DeferredItem<Item> longRod,
            DeferredItem<Item> flawlessGem, DeferredItem<Item> exquisiteGem) {

        public List<DeferredItem<? extends Item>> blockDisplayOrder() {
            List<DeferredItem<? extends Item>> list = new ArrayList<>();
            addIfPresent(list, ore); addIfPresent(list, deepslateOre); addIfPresent(list, netherOre); addIfPresent(list, endOre);
            return list;
        }

        public List<DeferredItem<? extends Item>> materialDisplayOrder() {
            List<DeferredItem<? extends Item>> list = new ArrayList<>();
            addIfPresent(list, rawMaterial); addIfPresent(list, ingot); addIfPresent(list, nugget);
            addIfPresent(list, flawlessGem); addIfPresent(list, exquisiteGem);
            addIfPresent(list, dust); addIfPresent(list, dirtyDust); addIfPresent(list, purifiedDust);
            addIfPresent(list, plate); addIfPresent(list, doublePlate); addIfPresent(list, rod);  addIfPresent(list, longRod);
            addIfPresent(list, ring); addIfPresent(list, gear); addIfPresent(list, spring); addIfPresent(list, bolt); addIfPresent(list, screw);
            return list;
        }

        private static void addIfPresent(List<DeferredItem<? extends Item>> out, DeferredItem<? extends Item> entry) {
            if (entry != null) out.add(entry);
        }
    }

    public record MachineSetRegistration(Map<TGEMachineType, DeferredItem<Item>> items) {
        public DeferredItem<Item> item(TGEMachineType type) { return items.get(type); }
        public List<DeferredItem<? extends Item>> displayOrder() {
            List<DeferredItem<? extends Item>> list = new ArrayList<>();
            for (TGEMachineType type : TGEMachineType.values()) {
                DeferredItem<Item> item = items.get(type);
                if (item != null) list.add(item);
            }
            return list;
        }
    }
}