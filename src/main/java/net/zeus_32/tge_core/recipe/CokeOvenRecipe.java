package net.zeus_32.tge_core.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class CokeOvenRecipe implements Recipe<CokeOvenRecipeInput> {
    private final Ingredient inputItem;
    private final ItemStack output;

    public CokeOvenRecipe(Ingredient inputItem, ItemStack output) {
        this.inputItem = inputItem;
        this.output = output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    public boolean matches(CokeOvenRecipeInput cokeOvenRecipeInput, Level level) {
        if (level.isClientSide()) {
            return false;
        }
        return inputItem.test(cokeOvenRecipeInput.getItem(0));
    }

    @Override
    public ItemStack assemble(CokeOvenRecipeInput cokeOvenRecipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.COKE_OVEN_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.COKE_OVEN_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<CokeOvenRecipe> {
        public static final MapCodec<CokeOvenRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(CokeOvenRecipe::getInputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(CokeOvenRecipe::getOutput)
        ).apply(inst, CokeOvenRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CokeOvenRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, CokeOvenRecipe::getInputItem,
                        ItemStack.STREAM_CODEC, CokeOvenRecipe::getOutput,
                        CokeOvenRecipe::new);

        @Override
        public MapCodec<CokeOvenRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CokeOvenRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

    public Ingredient getInputItem() {
        return inputItem;
    }

    public ItemStack getOutput() {
        return output;
    }
}