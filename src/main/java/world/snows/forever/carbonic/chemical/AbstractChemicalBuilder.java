package world.snows.forever.carbonic.chemical;

import net.minecraft.network.chat.MutableComponent;
import world.snows.forever.carbonic.util.Builder;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class AbstractChemicalBuilder<T extends Chemical<T>> implements Builder<T> {
    protected String objectId;
    protected MutableComponent translatableName;

    protected void setObjectId(String id) {
        this.objectId = id;
    }

    protected void setTranslatableName(MutableComponent translatableName) {
        this.translatableName = translatableName;
    }

    @Nullable
    public String getObjectId() {
        return this.objectId;
    }

    @Nullable
    public MutableComponent getTranslatableName() {
        return this.translatableName;
    }
}
