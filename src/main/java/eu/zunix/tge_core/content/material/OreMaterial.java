package eu.zunix.tge_core.content.material;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public enum OreMaterial {
    ALUMINIUM("aluminium", "Aluminium", "Al", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    ANTIMONY("antimony", "Antimony", "Sb", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    BRASS("brass", "Brass", "CuZn", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    BRONZE("bronze", "Bronze", "CuSn", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    CHROMIUM("chromium", "Chromium", "Cr", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    COBALT("cobalt", "Cobalt", "Co", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    CONSTANTAN("constantan", "Constantan", "CuNi", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    COPPER("copper", "Copper", "Cu", Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE),
    CUPRONICKEL("cupronickel", "Cupronickel", "CuNi", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    ELECTRUM("electrum", "Electrum", "AuAg", Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE),
    ENDERIUM("enderium", "Enderium", "End", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    GOLD("gold", "Gold", "Au", Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE),
    INVAR("invar", "Invar", "FeNi", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    IRIDIUM("iridium", "Iridium", "Ir", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    IRON("iron", "Iron", "Fe", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    LEAD("lead", "Lead", "Pb", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    LITHIUM("lithium", "Lithium", "Li", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    LUMIUM("lumium", "Lumium", "Lu", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    NAQUADAH("naquadah", "Naquadah", "Nq", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    NICKEL("nickel", "Nickel", "Ni", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    OSMIUM("osmium", "Osmium", "Os", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    PLATINUM("platinum", "Platinum", "Pt", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    RED_ALLOY("red_alloy", "Red Alloy", "RA", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    SILICON("silicon", "Silicon", "Si", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    SILVER("silver", "Silver", "Ag", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    SOUL_INFUSED("soul_infused", "Soul Infused", "SI", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    STAINLESS_STEEL("stainless_steel", "Stainless Steel", "FeCrNi", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    STEEL("steel", "Steel", "FeC", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    TIN("tin", "Tin", "Sn", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    TITANIUM("titanium", "Titanium", "Ti", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    TUNGSTEN("tungsten", "Tungsten", "W", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    URANIUM("uranium", "Uranium", "U", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    WROUGHT_IRON("wrought_iron", "Wrought Iron", "Fe", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE),
    ZINC("zinc", "Zinc", "Zn", Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE);

    private final String id;
    private final String displayName;
    private final String formula;
    private final Block baseOreTemplate;
    private final Block deepslateOreTemplate;

    OreMaterial(String id, String displayName, String formula, Block baseOreTemplate, Block deepslateOreTemplate) {
        this.id = id;
        this.displayName = displayName;
        this.formula = formula;
        this.baseOreTemplate = baseOreTemplate;
        this.deepslateOreTemplate = deepslateOreTemplate;
    }

    public String id() {
        return id;
    }

    public String displayName() {
        return displayName;
    }

    public String formula() {
        return formula;
    }

    public Block baseOreTemplate() {
        return baseOreTemplate;
    }

    public Block deepslateOreTemplate() {
        return deepslateOreTemplate;
    }

    public String oreId() {
        return id + "_ore";
    }

    public String deepslateOreId() {
        return "deepslate_" + id + "_ore";
    }

    public String rawMaterialId() {
        return "raw_" + id;
    }

    public String ingotId() {
        return id + "_ingot";
    }

    public String dustId() {
        return id + "_dust";
    }

    public String dirtyDustId() {
        return "dirty_" + id + "_dust";
    }

    public String rodId() {
        return id + "_rod";
    }

    public String plateId() {
        return id + "_plate";
    }

    public String gearId() {
        return id + "_gear";
    }

    public String boltId() {
        return id + "_bolt";
    }

    public String screwId() {
        return id + "_screw";
    }

    public String nuggetId() {
        return id + "_nugget";
    }

    public String longRodId() {
        return id + "_long_rod";
    }
}
