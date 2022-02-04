package net.savagedev.worldtimer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.savagedev.worldtimer.config.WorldTimerConfig;
import net.savagedev.worldtimer.listeners.HudRenderListener;

import java.nio.file.Path;

public class WorldTimerMod implements ModInitializer {
    private final Path dataPath = FabricLoader.getInstance().getConfigDir().resolve("worldtimer");
    private final WorldTimerConfig config = new WorldTimerConfig(this.dataPath.resolve("config.yml"));

    @Override
    public void onInitialize() {
        HudRenderCallback.EVENT.register(new HudRenderListener(this));
    }

    public WorldTimerConfig getConfig() {
        return this.config;
    }
}
