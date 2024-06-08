package net.dadamalda.learnmodding.datagen;

import net.dadamalda.learnmodding.LearnModding;
import net.dadamalda.learnmodding.block.ModBlocks;
import net.dadamalda.learnmodding.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> RUBY_SMELTABLES = List.of(
            ModItems.RAW_RUBY.get(),
            ModBlocks.RUBY_ORE.get(),
            ModBlocks.DEEPSLATE_RUBY_ORE.get()
    );

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreBlasting(pWriter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY.get(), 1.0f, 100, "ruby");
        oreSmelting(pWriter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY.get(), 1.0f, 200, "ruby");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUBY_STAFF.get())
                .pattern("  #")
                .pattern(" / ")
                .pattern("@  ")
                .define('#', ModBlocks.RUBY_BLOCK.get())
                .define('/', Items.STICK)
                .define('@', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(pWriter);

        threeByThreePackUnpack(pWriter, RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get(), ModItems.RUBY.get());
        threeByThreePackUnpack(pWriter, RecipeCategory.MISC, ModBlocks.RAW_RUBY_BLOCK.get(), ModItems.RAW_RUBY.get());

        glassDyeLike(pWriter, RecipeCategory.FOOD, ModItems.RUBY_INFUSED_BEEF.get(), ModItems.RUBY.get(), Items.COOKED_BEEF);
        glassDyeLike(pWriter, RecipeCategory.MISC, ModItems.RUBY_INFUSED_COAL.get(), ModItems.RUBY.get(), Items.COAL);
    }

    protected static void threeByThreePackUnpack(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeCategory pCategory, ItemLike pPacked, ItemLike pUnpacked) {
        threeByThreePacker(pFinishedRecipeConsumer, pCategory, pPacked, pUnpacked);
        ShapelessRecipeBuilder.shapeless(pCategory, pUnpacked, 9)
                .requires(pPacked)
                .unlockedBy(getHasName(pPacked), has(pPacked))
                .save(pFinishedRecipeConsumer);
    }

    protected static void glassDyeLike(Consumer<FinishedRecipe> pWriter, RecipeCategory pCategory, ItemLike dyedGlass, ItemLike dye, ItemLike glass) {
        ShapedRecipeBuilder.shaped(pCategory, dyedGlass, 8)
                .pattern("###")
                .pattern("#.#")
                .pattern("###")
                .define('#', glass)
                .define('.', dye)
                .unlockedBy(getHasName(glass), has(glass))
                .unlockedBy(getHasName(dye), has(dye))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, LearnModding.MODID + ":" +getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }

}
