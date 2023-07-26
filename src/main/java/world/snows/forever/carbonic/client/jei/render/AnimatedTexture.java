package world.snows.forever.carbonic.client.jei.render;

import mezz.jei.api.gui.drawable.IDrawableAnimated;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import world.snows.forever.carbonic.util.TickTimer;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

@ParametersAreNonnullByDefault
public class AnimatedTexture implements IDrawableAnimated {
    private final ResourceLocation texture;
    private final int width;
    private final int height;
    private final int maxFrames;
    private int currentFrame;
    private final TickTimer timer;

    public AnimatedTexture(ResourceLocation texture, int ticksPerFrame) throws IOException {
        // TODO: may be expensive on JEI startup, look into more efficient methods when needed
        BufferedImage img = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader()
                .getResourceAsStream(Paths.get("assets", texture.getNamespace(), texture.getPath()).toString())));

        this.texture = texture;
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.maxFrames = this.height / this.width;
        this.currentFrame = 0;
        this.timer = new TickTimer(ticksPerFrame);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset) {
//        RenderSystem.setShader(GameRenderer::getPositionTexShader);
//        RenderSystem.setShaderTexture(0, this.texture);
        guiGraphics.blit(this.texture, 0, 0, xOffset, yOffset + this.currentFrame * this.width, this.width, this.width, this.width, this.height);

        if (this.timer.isElapsed()) {
            this.currentFrame = (this.currentFrame + 1) % maxFrames;
            this.timer.reset();
        }
    }
}
