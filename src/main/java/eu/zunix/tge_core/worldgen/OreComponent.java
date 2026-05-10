package eu.zunix.tge_core.worldgen;

public record OreComponent(String blockId, double density) {
    public OreComponent {
        if (density < 0.0 || density > 1.0) {
            throw new IllegalArgumentException("OreComponent density must be between 0.0 and 1.0");
        }
    }
}
