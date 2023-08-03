package world.snows.forever.carbonic.chemical;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import world.snows.forever.carbonic.Carbonic;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
public class ChemicalDeferredRegister<T extends Chemical<T>> {
    private final DeferredRegister<T> registry;
    private @Nullable Supplier<IForgeRegistry<T>> registeredChemicals;

    public ChemicalDeferredRegister(ResourceKey<? extends Registry<T>> resourceKey) {
        this.registry = DeferredRegister.create(resourceKey, Carbonic.MOD_ID);
        this.registeredChemicals = null;
    }

    public RegistryObject<T> register(String name, Supplier<T> object) {
        return this.registry.register(name, object);
    }

    public void create(IEventBus bus, Supplier<RegistryBuilder<T>> regBuilder) {
        this.registeredChemicals = this.registry.makeRegistry(regBuilder);
        this.registry.register(bus);
    }

    @Nullable
    public final T getChemicalFromId(ResourceLocation id) throws NullPointerException {
        if (this.registeredChemicals == null) {
            throw new NullPointerException("chemicals have not been registered yet");
        }

        return this.registeredChemicals.get().getValue(id);
    }

    @Nullable
    public final ResourceLocation getIdFromChemical(T chemical) {
        if (this.registeredChemicals == null) {
            throw new NullPointerException("chemicals have not been registered yet");
        }

        return this.registeredChemicals.get().getKey(chemical);
    }

    @Nonnull
    public final ResourceLocation getRegistryLocation() {
        return this.registry.getRegistryKey().location();
    }

    @Nonnull
    public final MutableComponent getTranslatableComponent(T chemical) {
        return Component.translatable(String.format("%s.%s.%s", this.getRegistryLocation().getPath(),
                this.getRegistryLocation().getNamespace(),
                Objects.requireNonNull(this.getIdFromChemical(chemical)).getPath()));
    }

    public final Collection<RegistryObject<T>> getEntries() {
        return this.registry.getEntries();
    }
}