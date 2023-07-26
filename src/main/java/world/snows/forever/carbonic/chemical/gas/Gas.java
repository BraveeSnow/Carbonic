package world.snows.forever.carbonic.chemical.gas;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import world.snows.forever.carbonic.chemical.Chemical;

import javax.annotation.Nonnull;

public class Gas implements Chemical<Gas> {
    private final String objectId;
    private final MutableComponent namespace;
    private final ResourceLocation texture;
    private final int tint;


    protected Gas(GasBuilder builder) {
        this.objectId = builder.getObjectId();
        this.namespace = builder.getTranslatableName();
        this.texture = builder.getTexture();
        this.tint = builder.getColor();
    }

    @Nonnull
    @Override
    public Gas getChemical() {
        return this;
    }

    @Nonnull
    @Override
    public String getObjectId() {
        return this.objectId;
    }

    @Nonnull
    @Override
    public MutableComponent getNamespace() {
        return this.namespace;
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
