package eu.zunix.tge_core.registry;

import eu.zunix.tge_core.content.item.AxeToolItem;
import eu.zunix.tge_core.content.item.HoeToolItem;
import eu.zunix.tge_core.content.item.PickaxeToolItem;
import eu.zunix.tge_core.content.item.ShovelToolItem;
import eu.zunix.tge_core.content.item.SwordToolItem;
import eu.zunix.tge_core.content.item.ToolItem;
import eu.zunix.tge_core.content.tool.ToolMaterial;
import eu.zunix.tge_core.content.tool.ToolTier;
import eu.zunix.tge_core.content.tool.ToolType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.registries.DeferredItem;

public final class Tools {
    public static final ToolMaterial IRON =
            new ToolMaterial("iron", "Iron", "Iron", "2", ChatFormatting.GRAY, 256, new ToolTier(Tiers.IRON, 256));
    public static final ToolMaterial DIAMOND =
            new ToolMaterial("diamond", "Diamond", "Diamond", "3", ChatFormatting.AQUA, 1024, new ToolTier(Tiers.DIAMOND, 1024));
    public static final List<ToolMaterial> MATERIALS = List.of(IRON, DIAMOND);

    private static final List<Entry> ENTRIES = new ArrayList<>();
    private static boolean bootstrapped;

    private Tools() {
    }

    public static void bootstrap() {
        if (bootstrapped) {
            return;
        }
        bootstrapped = true;

        for (ToolType type : ToolType.values()) {
            for (ToolMaterial material : MATERIALS) {
                addMaterial(type, material);
            }
        }
    }

    public static Entry addMaterial(ToolType type, ToolMaterial material) {
        String id = material.id() + "_" + type.id();
        DeferredItem<? extends Item> item = ItemRegistry.register(id, factory(type, material), properties(type, material), false);
        Entry entry = new Entry(id, type, material, item);
        ENTRIES.add(entry);
        return entry;
    }

    private static Function<Item.Properties, ? extends Item> factory(ToolType type, ToolMaterial material) {
        return switch (type) {
            case PICKAXE -> properties -> new PickaxeToolItem(material, properties);
            case AXE -> properties -> new AxeToolItem(material, properties);
            case SHOVEL -> properties -> new ShovelToolItem(material, properties);
            case HOE -> properties -> new HoeToolItem(material, properties);
            case SWORD -> properties -> new SwordToolItem(material, properties);
            default -> properties -> new ToolItem(type, material, properties);
        };
    }

    private static Item.Properties properties(ToolType type, ToolMaterial material) {
        Item.Properties properties = new Item.Properties();
        return switch (type) {
            case PICKAXE -> properties.attributes(PickaxeItem.createAttributes(material.tier(), 1.0F, -2.8F));
            case AXE -> properties.attributes(AxeItem.createAttributes(material.tier(), 6.0F, -3.1F));
            case SHOVEL -> properties.attributes(ShovelItem.createAttributes(material.tier(), 1.5F, -3.0F));
            case HOE -> properties.attributes(HoeItem.createAttributes(material.tier(), -2.0F, -1.0F));
            case SWORD -> properties.attributes(swordAttributes(material));
            default -> properties.durability(material.durability());
        };
    }

    private static ItemAttributeModifiers swordAttributes(ToolMaterial material) {
        return SwordItem.createAttributes(material.tier(), 3, -2.4F)
                .withModifierAdded(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath("tge", "duelist_range"),
                                2.0D,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND);
    }

    public static List<Entry> entries() {
        if (ENTRIES.isEmpty()) {
            bootstrapped = false;
            bootstrap();
        }
        return Collections.unmodifiableList(ENTRIES);
    }

    public record Entry(String id, ToolType type, ToolMaterial material, DeferredItem<? extends Item> item) {
        public String displayName() {
            return material.displayName() + " " + type.displayName();
        }
    }
}
