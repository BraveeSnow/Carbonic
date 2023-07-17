package world.snows.forever.carbonic;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.snows.forever.carbonic.registry.*;

@Mod(Carbonic.MOD_ID)
public class Carbonic {
    public static final String MOD_ID = "carbonic";
    public static final Logger LOGGER = LoggerFactory.getLogger(Carbonic.class);

    public Carbonic() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        LOGGER.info("Initializing Carbonic...");

        BlockRegistry.BLOCK_REGISTRY.register(bus);
        ItemRegistry.ITEM_REGISTRY.register(bus);
        BlockEntityRegistry.BLOCK_ENTITY_REGISTRY.register(bus);
        MenuRegistry.MENU_REGISTRY.register(bus);

        CreativeTabRegistry.TAB_REGISTRY.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
