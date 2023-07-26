package world.snows.forever.carbonic.registry.custom;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import world.snows.forever.carbonic.Carbonic;
import world.snows.forever.carbonic.chemical.gas.Gas;

public class CarbonicRegistryKeys {
    public static final ResourceKey<Registry<Gas>> GAS = ResourceKey.createRegistryKey(new ResourceLocation(Carbonic.MOD_ID, "gas"));
}
