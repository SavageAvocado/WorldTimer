package net.savagedev.worldtimer.listeners;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class HudRenderListener implements HudRenderCallback {
    private int x, y = 0;

    private int xAccel = 1;
    private int yAccel = 1;

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        final float windowHeight = 100;//MinecraftClient.getInstance().getWindow().getHeight();
        final float windowWidth = 200;//MinecraftClient.getInstance().getWindow().getWidth();

        MinecraftClient.getInstance().inGameHud.getTextRenderer().draw(matrixStack, "DVD", this.x, this.y, 255);

        if (this.x >= windowWidth) {
            this.xAccel = -this.xAccel;
        } else {
            this.x += this.xAccel;
        }
        if (this.y >= windowHeight) {
            this.yAccel = -this.yAccel;
        } else {
            this.y += this.yAccel;
        }
    }
}
