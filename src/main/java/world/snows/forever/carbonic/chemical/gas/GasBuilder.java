package world.snows.forever.carbonic.chemical.gas;

import net.minecraft.resources.ResourceLocation;
import world.snows.forever.carbonic.chemical.AbstractChemicalBuilder;

public class GasBuilder extends AbstractChemicalBuilder<Gas> {
    private final ResourceLocation texture;
    private int tint = -1;

    public GasBuilder(ResourceLocation texture) {
        this.texture = texture;
    }

    public GasBuilder() {
        this(new ResourceLocation("textures/block/water_still.png"));
    }

    public GasBuilder setColor(int color) {
        this.tint = color;
        return this;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public int getColor() {
        return this.tint;
    }

    public Gas build() {
        return new Gas(this);
    }
}
