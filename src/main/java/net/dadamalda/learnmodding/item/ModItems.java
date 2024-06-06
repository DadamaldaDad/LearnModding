package net.dadamalda.learnmodding.item;

import net.dadamalda.learnmodding.LearnModding;
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
            () -> new RubyStaffItem(new Item.Properties().durability(200)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
