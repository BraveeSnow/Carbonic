package world.snows.forever.carbonic.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import world.snows.forever.carbonic.Carbonic;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Carbonic.MOD_ID);

    public static final RegistryObject<Item> CHLOROFORM = ITEM_REGISTRY.register("chloroform", () -> new Item(new Item.Properties()));
}
