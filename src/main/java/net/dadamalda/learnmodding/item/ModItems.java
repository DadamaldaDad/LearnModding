package net.dadamalda.learnmodding.item;

import net.dadamalda.learnmodding.LearnModding;
import net.dadamalda.learnmodding.item.custom.FuelItem;
import net.dadamalda.learnmodding.item.custom.RubyStaffItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LearnModding.MODID);

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_RUBY = ITEMS.register("raw_ruby",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUBY_STAFF = ITEMS.register("ruby_staff",
            () -> new RubyStaffItem(new Item.Properties().durability(350)));

    public static final RegistryObject<Item> RUBY_INFUSED_BEEF = ITEMS.register("ruby_infused_beef",
            () -> new Item(new Item.Properties().food(ModFoods.RUBY_INFUSED_BEEF)));
    public static final RegistryObject<Item> RUBY_INFUSED_COAL = ITEMS.register("ruby_infused_coal",
            () -> new FuelItem(new Item.Properties(), 16*200));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
