package world.snows.forever.carbonic.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import world.snows.forever.carbonic.Carbonic;
import world.snows.forever.carbonic.gui.ElectrolysisTankMenu;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ElectrolysisTankScreen extends AbstractContainerScreen<ElectrolysisTankMenu> {
    private static final ResourceLocation SCREEN_TEXTURE = new ResourceLocation(Carbonic.MOD_ID,
            "textures/gui/electrolysis_tank_gui.png");

    private final ElectrolysisTankMenu menu;

    public ElectrolysisTankScreen(ElectrolysisTankMenu menu, Inventory inv, Component component) {
        super(menu, inv, component);
        this.menu = menu;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, SCREEN_TEXTURE);
        graphics.blit(SCREEN_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        renderMembraneSeparator(graphics);
    }

    private void renderMembraneSeparator(GuiGraphics graphics) {
        if (menu.hasMembraneCell()) {
            graphics.blit(SCREEN_TEXTURE, this.leftPos + 87, this.topPos + 18, 176, 0, 2, 38);
        }
    }
}
