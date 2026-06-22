package eu.zunix.tge_core.datagen.helper;

import eu.zunix.tge_core.content.tool.ToolType;

public final class GeneratedData {
    public static final String[] ITEMS = {
            "glass_dust",
            "quartz_dust",
            "flint_dust",
            "clay_dust",
            "brick_dust",
            "fire_clay_dust",
            "compressed_fire_brick",
            "fire_brick",
            "unfired_clay_brick",
            "brick_mold"
    };

    public static final String[] ORE_AND_ALLOY_MATERIALS = {
            "aluminium",
            "antimony",
            "brass",
            "bronze",
            "chromium",
            "cobalt",
            "constantan",
            "copper",
            "cupronickel",
            "diamond",
            "electrum",
            "enderium",
            "gold",
            "invar",
            "iridium",
            "iron",
            "lead",
            "lithium",
            "lumium",
            "naquadah",
            "nickel",
            "osmium",
            "platinum",
            "red_alloy",
            "silver",
            "soul_infused",
            "stainless_steel",
            "steel",
            "tin",
            "titanium",
            "tungsten",
            "uranium",
            "wrought_iron",
            "zinc"
    };

    public static final String HAMMER_TAG = "tge:tools/hammers";
    public static final String FILE_TAG = "tge:tools/files";
    public static final String SAW_TAG = "tge:tools/saws";
    public static final String MORTAR_TAG = "tge:tools/mortars";
    public static final String WRENCH_TAG = "tge:tools/wrenches";
    public static final String WIRE_CUTTERS_TAG = "tge:tools/wire_cutters";
    public static final String SCREWDRIVER_TAG = "tge:tools/screwdrivers";
    public static final String[] MINECRAFT_TOOL_TAGS = {
            "pickaxes",
            "axes",
            "shovels",
            "hoes",
            "swords"
    };
    public static final ToolTag[] TOOL_TAGS = {
            new ToolTag("hammers", ToolType.HAMMER),
            new ToolTag("wrenches", ToolType.WRENCH),
            new ToolTag("saws", ToolType.SAW),
            new ToolTag("files", ToolType.FILE),
            new ToolTag("mortars", ToolType.MORTAR),
            new ToolTag("wire_cutters", ToolType.WIRE_CUTTERS),
            new ToolTag("screwdrivers", ToolType.SCREWDRIVER)
    };

    private GeneratedData() {
    }

    public static String tagMaterial(String material) {
        return "aluminium".equals(material) ? "aluminum" : material;
    }

    public record ToolTag(String name, ToolType type) {
    }
}
