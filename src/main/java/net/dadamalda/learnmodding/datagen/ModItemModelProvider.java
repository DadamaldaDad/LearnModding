package net.dadamalda.learnmodding.datagen;

import net.dadamalda.learnmodding.LearnModding;
import net.dadamalda.learnmodding.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LearnModding.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.RUBY);
        simpleItem(ModItems.RAW_RUBY);
        simpleItem(ModItems.RUBY_STAFF);
        simpleItem(ModItems.RUBY_INFUSED_BEEF);
        simpleItem(ModItems.RUBY_INFUSED_COAL);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(LearnModding.MODID, "item/" + item.getId().getPath()));
    }
}
