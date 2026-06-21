package eu.zunix.tge_core.registry;

import eu.zunix.tge_core.content.item.TGEAxeItem;
import eu.zunix.tge_core.content.item.TGEHoeItem;
import eu.zunix.tge_core.content.item.TGEPickaxeItem;
import eu.zunix.tge_core.content.item.TGEShovelItem;
import eu.zunix.tge_core.content.item.TGESwordItem;
import eu.zunix.tge_core.content.item.TGEToolItem;
import eu.zunix.tge_core.content.tool.TGEToolMaterial;
import eu.zunix.tge_core.content.tool.TGETier;
import eu.zunix.tge_core.content.tool.TGEToolType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;

public final class TGETools {
    public static final TGEToolMaterial IRON =
            new TGEToolMaterial("iron", "Iron", "iron", 256, new TGETier(Tiers.IRON, 256));

    private static final List<Entry> ENTRIES = new ArrayList<>();
    private static boolean bootstrapped;

    private TGETools() {
    }

    public static void bootstrap() {
        if (bootstrapped) {
            return;
        }
        bootstrapped = true;

        addMaterial(TGEToolType.HAMMER, IRON);
        addMaterial(TGEToolType.WRENCH, IRON);
        addMaterial(TGEToolType.SAW, IRON);
        addMaterial(TGEToolType.FILE, IRON);
        addMaterial(TGEToolType.MORTAR, IRON);
        addMaterial(TGEToolType.WIRE_CUTTERS, IRON);
        addMaterial(TGEToolType.SCREWDRIVER, IRON);
        addMaterial(TGEToolType.PICKAXE, IRON);
        addMaterial(TGEToolType.AXE, IRON);
        addMaterial(TGEToolType.SHOVEL, IRON);
        addMaterial(TGEToolType.HOE, IRON);
        addMaterial(TGEToolType.SWORD, IRON);
    }

    public static Entry addMaterial(TGEToolType type, TGEToolMaterial material) {
        String id = material.id() + "_" + type.id();
        DeferredItem<? extends Item> item = TGEItemRegistry.register(id, factory(type, material), properties(type, material));
        Entry entry = new Entry(id, type, material, item);
        ENTRIES.add(entry);
        return entry;
    }

    private static Function<Item.Properties, ? extends Item> factory(TGEToolType type, TGEToolMaterial material) {
        return switch (type) {
            case PICKAXE -> properties -> new TGEPickaxeItem(material, properties);
            case AXE -> properties -> new TGEAxeItem(material, properties);
            case SHOVEL -> properties -> new TGEShovelItem(material, properties);
            case HOE -> properties -> new TGEHoeItem(material, properties);
            case SWORD -> properties -> new TGESwordItem(material, properties);
            default -> properties -> new TGEToolItem(type, material, properties);
        };
    }

    private static Item.Properties properties(TGEToolType type, TGEToolMaterial material) {
        Item.Properties properties = new Item.Properties();
        return switch (type) {
            case PICKAXE -> properties.attributes(PickaxeItem.createAttributes(material.tier(), 1.0F, -2.8F));
            case AXE -> properties.attributes(AxeItem.createAttributes(material.tier(), 6.0F, -3.1F));
            case SHOVEL -> properties.attributes(ShovelItem.createAttributes(material.tier(), 1.5F, -3.0F));
            case HOE -> properties.attributes(HoeItem.createAttributes(material.tier(), -2.0F, -1.0F));
            case SWORD -> properties.attributes(SwordItem.createAttributes(material.tier(), 3, -2.4F));
            default -> properties.durability(material.durability());
        };
    }

    public static List<Entry> entries() {
        return Collections.unmodifiableList(ENTRIES);
    }

    public record Entry(String id, TGEToolType type, TGEToolMaterial material, DeferredItem<? extends Item> item) {
        public String displayName() {
            return material.displayName() + " " + type.displayName();
        }
    }
}
