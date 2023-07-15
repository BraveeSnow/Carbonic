package world.snows.forever.carbonic.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import world.snows.forever.carbonic.Carbonic;

@Mod.EventBusSubscriber(modid = Carbonic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> TAB_REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Carbonic.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CARBONIC_COMMON = TAB_REGISTRY.register("carbonic_common", () ->
            CreativeModeTab.builder().title(Component.translatable("itemGroup.carbonic_common")).build());

    @SubscribeEvent
    public static void loadCommonTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab().equals(CARBONIC_COMMON.get())) {
            ItemRegistry.ITEM_REGISTRY.getEntries().forEach(event::accept);
        }
    }
}
