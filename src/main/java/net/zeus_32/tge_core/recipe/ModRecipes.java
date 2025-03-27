package net.zeus_32.tge_core.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zeus_32.tge_core.TGECore;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, TGECore.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, TGECore.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CokeOvenRecipe>> COKE_OVEN_SERIALIZER =
            SERIALIZERS.register("coke_oven", CokeOvenRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<CokeOvenRecipe>> COKE_OVEN_TYPE =
            TYPES.register("coke_oven", () -> new RecipeType<CokeOvenRecipe>() {
                @Override
                public String toString() {
                    return "coke_oven";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
