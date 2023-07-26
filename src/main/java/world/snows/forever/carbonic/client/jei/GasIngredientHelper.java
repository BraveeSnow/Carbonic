package world.snows.forever.carbonic.client.jei;

import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.resources.ResourceLocation;
import world.snows.forever.carbonic.chemical.gas.Gas;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class GasIngredientHelper implements IIngredientHelper<Gas> {
    @Nonnull
    @Override
    public IIngredientType<Gas> getIngredientType() {
        return new IIngredientType<>() {
            @Nonnull
            @Override
            public Class<? extends Gas> getIngredientClass() {
                return Gas.class;
            }
        };
    }

    @Nonnull
    @Override
    public String getDisplayName(Gas ingredient) {
        return ingredient.getNamespace().getString();
    }

    @Nonnull
    @Override
    public String getUniqueId(Gas ingredient, UidContext context) {
        return ingredient.getObjectId();
    }

    @Nonnull
    @Override
    public ResourceLocation getResourceLocation(Gas ingredient) {
        return new ResourceLocation(ingredient.getObjectId());
    }

    @Nonnull
    @Override
    public Gas copyIngredient(Gas ingredient) {
        return ingredient;
    }

    @Nonnull
    @Override
    public String getErrorInfo(@Nullable Gas ingredient) {
        if (ingredient == null) {
            return "Ingredient was null";
        }

        return "On ingredient " + ingredient.getObjectId();
    }
}
