package net.satisfy.vinery.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.satisfy.vinery.client.gui.handler.ApplePressGuiHandler;
import net.satisfy.vinery.core.util.VineryIdentifier;

@Environment(EnvType.CLIENT)
public class ApplePressGui extends AbstractContainerScreen<ApplePressGuiHandler> {
    public static final ResourceLocation TEXTURE = new VineryIdentifier("textures/gui/apple_press_gui.png");

    public static final int MASHING_BAR_X = 40;
    public static final int MASHING_BAR_Y = 17;
    public static final int MASHING_BAR_WIDTH = 24;
    public static final int MASHING_BAR_HEIGHT = 38;
    public static final int MASHING_BAR_U = 176;
    public static final int MASHING_BAR_V = 0;

    public static final int FERMENTING_BAR_X = 101;
    public static final int FERMENTING_BAR_Y = 18;
    public static final int FERMENTING_BAR_WIDTH = 10;
    public static final int FERMENTING_BAR_HEIGHT = 27;
    public static final int FERMENTING_BAR_U = 176;
    public static final int FERMENTING_BAR_V = 47;

    public ApplePressGui(ApplePressGuiHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        renderProgressArrows(guiGraphics, x, y);
    }

    private void renderProgressArrows(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCrafting(0)) {
            int height = menu.getScaledProgress(0);
            int xPosition = x + MASHING_BAR_X;
            int yPosition = y + MASHING_BAR_Y + height;
            int textureV = MASHING_BAR_V + height;
            int renderHeight = MASHING_BAR_HEIGHT - height;
            guiGraphics.blit(TEXTURE, xPosition, yPosition, MASHING_BAR_U, textureV, MASHING_BAR_WIDTH, renderHeight);
        }
        if (menu.isCrafting(1)) {
            int height = menu.getScaledProgress(1);
            int xPosition = x + FERMENTING_BAR_X;
            int yPosition = y + FERMENTING_BAR_Y + FERMENTING_BAR_HEIGHT - height;
            guiGraphics.blit(TEXTURE, xPosition, yPosition, FERMENTING_BAR_U, FERMENTING_BAR_V + FERMENTING_BAR_HEIGHT - height, FERMENTING_BAR_WIDTH, height);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
