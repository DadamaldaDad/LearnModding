package net.dadamalda.learnmodding.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static FoodProperties RUBY_INFUSED_BEEF = new FoodProperties.Builder()
            .nutrition(12)
            .saturationMod(1.0f)
            .meat()
            .build();
}
