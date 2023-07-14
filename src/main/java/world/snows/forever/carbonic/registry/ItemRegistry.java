package world.snows.forever.carbonic.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import world.snows.forever.carbonic.Carbonic;
import world.snows.forever.carbonic.item.ChemicalItem;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Carbonic.MOD_ID);

    // Elements


    // Compounds

    public static final RegistryObject<Item> SALT = ITEM_REGISTRY.register("salt",
            () -> new ChemicalItem("NaCl"));

    public static final RegistryObject<Item> BLEACH = ITEM_REGISTRY.register("bleach",
            () -> new ChemicalItem("NaClO"));
    public static final RegistryObject<Item> CHLOROFORM = ITEM_REGISTRY.register("chloroform",
            () -> new ChemicalItem("CHCl₃"));
    public static final RegistryObject<Item> TEFLON = ITEM_REGISTRY.register("teflon",
            () -> new ChemicalItem("C₂F₄"));
}
