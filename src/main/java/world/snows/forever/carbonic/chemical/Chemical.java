package world.snows.forever.carbonic.chemical;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public interface Chemical<T extends Chemical<T>> {
    @Nonnull
    T getChemical();
    @Nonnull
    ResourceLocation getTexture();

    default int getColor() {
        return 0xFFFFFF;
    }
}
