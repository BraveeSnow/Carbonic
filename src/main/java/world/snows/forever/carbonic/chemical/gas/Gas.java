package world.snows.forever.carbonic.chemical.gas;

import net.minecraft.resources.ResourceLocation;
import world.snows.forever.carbonic.chemical.Chemical;
import world.snows.forever.carbonic.registry.GasRegistry;

import javax.annotation.Nonnull;

public class Gas implements Chemical<Gas> {
    private final ResourceLocation texture;
    private final int tint;

    protected Gas(GasBuilder builder) {
        this.texture = builder.getTexture();
        this.tint = builder.getColor();
    }

    public static Gas from(ResourceLocation id) {
        return GasRegistry.GAS_REGISTRY.getChemicalFromId(id);
    }

    @Nonnull
    @Override
    public Gas getChemical() {
        return this;
    }

    @Nonnull
    @Override
    public ResourceLocation getTexture() {
        return this.texture;
    }

    @Override
    public int getColor() {
        return this.tint;
    }
}
