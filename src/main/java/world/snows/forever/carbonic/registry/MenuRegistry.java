package world.snows.forever.carbonic.registry;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import world.snows.forever.carbonic.Carbonic;
import world.snows.forever.carbonic.client.gui.ElectrolysisTankMenu;
import world.snows.forever.carbonic.client.screen.ElectrolysisTankScreen;

@Mod.EventBusSubscriber(modid = Carbonic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MenuRegistry {
    public static final DeferredRegister<MenuType<?>> MENU_REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES,
            Carbonic.MOD_ID);

    public static final RegistryObject<MenuType<ElectrolysisTankMenu>> ELECTROLYSIS_TANK_MENU = MENU_REGISTRY
            .register("electrolysis_tank_menu", () -> IForgeMenuType.create(ElectrolysisTankMenu::new));

    @SubscribeEvent
    public static void registerMenuScreens(FMLClientSetupEvent event) {
        MenuScreens.register(ELECTROLYSIS_TANK_MENU.get(), ElectrolysisTankScreen::new);
    }
}
