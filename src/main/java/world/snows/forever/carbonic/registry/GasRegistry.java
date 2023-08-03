package world.snows.forever.carbonic.registry;

import net.minecraftforge.registries.RegistryObject;
import world.snows.forever.carbonic.chemical.ChemicalDeferredRegister;
import world.snows.forever.carbonic.chemical.gas.Gas;
import world.snows.forever.carbonic.chemical.gas.GasBuilder;
import world.snows.forever.carbonic.registry.custom.CarbonicRegistryKeys;

public class GasRegistry {
    public static final ChemicalDeferredRegister<Gas> GAS_REGISTRY =
            new ChemicalDeferredRegister<>(CarbonicRegistryKeys.GAS);

    public static final RegistryObject<Gas> CHLORINE = GAS_REGISTRY.register("chlorine",
            new GasBuilder().setColor(0x93D8C280)::build);
}
