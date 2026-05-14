package eu.zunix.tge_core.content.material;

import java.util.EnumSet;
import java.util.Set;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public enum OreMaterial {
    // METALS
    ALUMINIUM(METAL("aluminium", "Aluminium", "Al", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),
    BRASS(METAL("brass", "Brass", "CuZn", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),
    BRONZE(METAL("bronze", "Bronze", "CuSn", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),
    COPPER(METAL("copper", "Copper", "Cu", Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE)),
    GOLD(METAL("gold", "Gold", "Au", Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE)),
    IRON(METAL("iron", "Iron", "Fe", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),
    LEAD(METAL("lead", "Lead", "Pb", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),
    NETHERITE(METAL("netherite", "Netherite", "Netherite", Blocks.ANCIENT_DEBRIS, Blocks.ANCIENT_DEBRIS)),
    NICKEL(METAL("nickel", "Nickel", "Ni", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),
    OSMIUM(METAL("osmium", "Osmium", "Os", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),
    SILICON(METAL("silicon", "Silicon", "Si", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),
    SILVER(METAL("silver", "Silver", "Ag", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),
    STEEL(METAL("steel", "Steel", "FeC", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),
    WROUGHT_IRON(METAL("wrought_iron", "Wrought Iron", "Fe", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),
    ZINC(METAL("zinc", "Zinc", "Zn", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE)),

    // GEMS
    DIAMOND(GEM("diamond", "Diamond", "C", Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE)),
    EMERALD(GEM("emerald", "Emerald", "Be3Al2Si6O18", Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE));

    private static record Spec(
            String id,
            String displayName,
            String formula,
            Block base,
            Block deep,
            boolean oreOnly,
            String itemCategory) {
    }

    private static Spec METAL(String id, String displayName, String formula, Block base, Block deep) {
        return new Spec(id, displayName, formula, base, deep, false, "metals");
    }

    private static Spec GEM(String id, String displayName, String formula, Block base, Block deep) {
        return new Spec(id, displayName, formula, base, deep, true, "gems");
    }

    OreMaterial(Spec s) {
        this(s.id(), s.displayName(), s.formula(), s.base(), s.deep(), s.oreOnly(), s.itemCategory());
    }

    private static final Set<OreMaterial> NO_ORE_AND_RAW = EnumSet.of(
            ALUMINIUM, BRASS, BRONZE, STEEL, WROUGHT_IRON, NETHERITE
    );

    private final String id;
    private final String displayName;
    private final String formula;
    private final Block baseOreTemplate;
    private final Block deepslateOreTemplate;
    private final boolean oreOnly;
    private final String itemCategory;

    OreMaterial(String id, String displayName, String formula, Block base, Block deep, boolean oreOnly, String itemCategory) {
        this.id = id;
        this.displayName = displayName;
        this.formula = formula;
        this.baseOreTemplate = base;
        this.deepslateOreTemplate = deep;
        this.oreOnly = oreOnly;
        this.itemCategory = itemCategory;
    }

    public String id() { return id; }
    public String displayName() { return displayName; }
    public String formula() { return formula; }
    public Block baseOreTemplate() { return baseOreTemplate; }
    public Block deepslateOreTemplate() { return deepslateOreTemplate; }
    public String itemCategory() { return itemCategory; }

    public String oreId() { return id + "_ore"; }
    public String deepslateOreId() { return "deepslate_" + id + "_ore"; }
    public String netherOreId() { return "nether_" + id + "_ore"; }
    public String endOreId() { return "end_" + id + "_ore"; }
    public String rawMaterialId() { return "raw_" + id; }
    public String gemId() { return id; }
    public String flawlessGemId() { return "flawless_" + id; }
    public String exquisiteGemId() { return "exquisite_" + id; }
    public String ingotId() { return hasGemForm() ? gemId() : id + "_ingot"; }
    public String dustId() { return id + "_dust"; }
    public String dirtyDustId() { return "dirty_" + id + "_dust"; }
    public String purifiedDustId() { return "purified_" + id + "_dust"; }
    public String rodId() { return id + "_rod"; }
    public String plateId() { return id + "_plate"; }
    public String doublePlateId() { return id + "_double_plate"; }
    public String ringId() { return id + "_ring"; }
    public String gearId() { return id + "_gear"; }
    public String springId() { return id + "_spring"; }
    public String boltId() { return id + "_bolt"; }
    public String screwId() { return id + "_screw"; }
    public String nuggetId() { return id + "_nugget"; }
    public String longRodId() { return id + "_long_rod"; }

    public boolean hasOreForm() { return this != SILICON && !NO_ORE_AND_RAW.contains(this); }
    public boolean hasRawMaterialForm() { return hasOreForm() && !oreOnly; }
    public boolean hasGemForm() { return "gems".equals(itemCategory); }
    public boolean hasIngotForm() { return hasGemForm() || !oreOnly; }
    public boolean hasDustForm() { return !oreOnly && this != SILICON; }
    public boolean hasDirtyDustForm() { return !oreOnly && this != SILICON; }
    public boolean hasPurifiedDustForm() { return !oreOnly && this != SILICON; }
    public boolean hasRodForm() { return !oreOnly && this != SILICON; }
    public boolean hasPlateForm() { return !oreOnly && this != SILICON; }
    public boolean hasDoublePlateForm() { return !oreOnly && this != SILICON; }
    public boolean hasRingForm() { return !oreOnly && this != SILICON; }
    public boolean hasGearForm() { return !oreOnly && this != SILICON; }
    public boolean hasSpringForm() { return !oreOnly && this != SILICON; }
    public boolean hasBoltForm() { return !oreOnly && this != SILICON; }
    public boolean hasScrewForm() { return !oreOnly && this != SILICON; }
    public boolean hasNuggetForm() { return !oreOnly && this != SILICON; }
    public boolean hasLongRodForm() { return !oreOnly && this != SILICON; }
}