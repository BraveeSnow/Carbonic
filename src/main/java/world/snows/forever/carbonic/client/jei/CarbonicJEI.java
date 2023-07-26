package world.snows.forever.carbonic.client.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.registration.IModIngredientRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import world.snows.forever.carbonic.Carbonic;
import world.snows.forever.carbonic.chemical.gas.Gas;
import world.snows.forever.carbonic.registry.GasRegistry;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@ParametersAreNonnullByDefault
@SuppressWarnings("unused")
public class CarbonicJEI implements IModPlugin {
    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Carbonic.MOD_ID, "core");
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registration) {
        registration.register(new IIngredientType<Gas>() {
            @Nonnull
            @Override
            public Class<? extends Gas> getIngredientClass() {
                return Gas.class;
            }
        }, GasRegistry.GAS_REGISTRY.getEntries().stream().map(RegistryObject::get).toList(), new GasIngredientHelper(), new GasIngredientRenderer());
    }
}
