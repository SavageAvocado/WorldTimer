package net.savagedev.worldtimer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.savagedev.worldtimer.listeners.HudRenderListener;

public class WorldTimerMod implements ModInitializer {

    @Override
    public void onInitialize() {
        HudRenderCallback.EVENT.register(new HudRenderListener());
    }
}
