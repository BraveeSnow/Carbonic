package world.snows.forever.carbonic.client.jei;

import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import world.snows.forever.carbonic.chemical.gas.Gas;
import world.snows.forever.carbonic.client.jei.render.AnimatedTexture;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.List;

@ParametersAreNonnullByDefault
@OnlyIn(Dist.CLIENT)
public class GasIngredientRenderer implements IIngredientRenderer<Gas> {
    private IDrawable texture = null;


    @Override
    public void render(GuiGraphics guiGraphics, Gas ingredient) {
        if (texture == null) {
            try {
                this.texture = new AnimatedTexture(ingredient.getTexture(), 10);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        final int color = ingredient.getColor();

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor((float) ((color >> 24) & 0xFF) / 255, (float) ((color >> 16) & 0xFF) / 255, (float) ((color >> 8) & 0xFF) / 255, (float) (color & 0xFF) / 255);
        texture.draw(guiGraphics);

        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }

    @Nonnull
    @Override
    public List<Component> getTooltip(Gas ingredient, TooltipFlag tooltipFlag) {
        return List.of(ingredient.getNamespace());
    }
}
