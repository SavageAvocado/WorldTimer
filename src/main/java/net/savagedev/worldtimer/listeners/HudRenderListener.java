package net.savagedev.worldtimer.listeners;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.savagedev.worldtimer.WorldTimerMod;
import net.savagedev.worldtimer.utils.TimeUtils;

import java.awt.*;

public class HudRenderListener implements HudRenderCallback {
    private static final Color MC_RED = new Color(255, 85, 85);

    private final WorldTimerMod context;

    public HudRenderListener(WorldTimerMod context) {
        this.context = context;
    }

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        final int scale = MinecraftClient.getInstance().options.guiScale;

        final int windowWidthScaled = MinecraftClient.getInstance().getWindow().getWidth() / (scale == 0 ? 8 : scale);
        final int windowHeightScaled = MinecraftClient.getInstance().getWindow().getHeight() / (scale == 0 ? 8 : scale);

        final long timePlayed = MinecraftClient.getInstance().player.getWorld().getTime();

        final String timeFormatted = TimeUtils.formatTime(timePlayed);

        final int strLength = MinecraftClient.getInstance().inGameHud.getTextRenderer().getWidth(timeFormatted);
        final int fontHeight = MinecraftClient.getInstance().inGameHud.getTextRenderer().fontHeight;

        final int x = windowWidthScaled - strLength - (this.context.getConfig().getXOffset() / (scale == 0 ? 8 : scale));
        final int y = windowHeightScaled - fontHeight - (this.context.getConfig().getYOffset() / (scale == 0 ? 8 : scale));

        MinecraftClient.getInstance().inGameHud.getTextRenderer().draw(matrixStack, timeFormatted, x, y, MC_RED.getRGB());
    }
}
