package world.snows.forever.carbonic.chemical;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import world.snows.forever.carbonic.Carbonic;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
public class ChemicalDeferredRegister<T extends Chemical<T>> {
    private final DeferredRegister<T> registry;

    public ChemicalDeferredRegister(ResourceKey<? extends Registry<T>> resourceKey) {
        this.registry = DeferredRegister.create(resourceKey, Carbonic.MOD_ID);
    }

    public RegistryObject<T> register(String name, AbstractChemicalBuilder<T> builder) {
        final String registryLocation = this.registry.getRegistryKey().location().toString();
        final String[] registrySplit = registryLocation.split(":");

        builder.setObjectId(registryLocation);
        builder.setTranslatableName(Component.translatable("%s.%s.%s".formatted(registrySplit[1], registrySplit[0], name)));

        return this.registry.register(name, builder::build);
    }

    public void create(IEventBus bus, Supplier<RegistryBuilder<T>> regBuilder) {
        this.registry.makeRegistry(regBuilder);
        this.registry.register(bus);
    }

    public final Collection<RegistryObject<T>> getEntries() {
        return this.registry.getEntries();
    }
}