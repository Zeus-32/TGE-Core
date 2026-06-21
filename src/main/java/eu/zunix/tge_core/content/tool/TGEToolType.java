package eu.zunix.tge_core.content.tool;

public enum TGEToolType {
    HAMMER("hammer", "Hammer", null),
    WRENCH("wrench", "Wrench", null),
    SAW("saw", "Saw", null),
    FILE("file", "File", null),
    MORTAR("mortar", "Mortar", null),
    WIRE_CUTTERS("wire_cutters", "Wire Cutters", null),
    SCREWDRIVER("screwdriver", "Screwdriver", null),
    PICKAXE("pickaxe", "Pickaxe", "pickaxes"),
    AXE("axe", "Axe", "axes"),
    SHOVEL("shovel", "Shovel", "shovels"),
    HOE("hoe", "Hoe", "hoes"),
    SWORD("sword", "Sword", "swords");

    private final String id;
    private final String displayName;
    private final String minecraftItemTag;

    TGEToolType(String id, String displayName, String minecraftItemTag) {
        this.id = id;
        this.displayName = displayName;
        this.minecraftItemTag = minecraftItemTag;
    }

    public String id() {
        return id;
    }

    public String displayName() {
        return displayName;
    }

    public String minecraftItemTag() {
        return minecraftItemTag;
    }
}
