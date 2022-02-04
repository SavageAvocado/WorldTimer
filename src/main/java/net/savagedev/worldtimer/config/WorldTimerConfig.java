package net.savagedev.worldtimer.config;

import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorldTimerConfig {
    private final YamlConfigurationLoader loader;

    private ConfigurationNode root;

    public WorldTimerConfig(Path path) {
        if (Files.notExists(path)) {
            final InputStream stream = this.getClass().getClassLoader().getResourceAsStream("config.yml");
            if (stream == null) {
                throw new RuntimeException("Failed to save default config! Stream null.");
            }
            try {
                Files.createDirectories(path.getParent());
                Files.copy(stream, path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.loader = YamlConfigurationLoader.builder()
                .path(path)
                .nodeStyle(NodeStyle.BLOCK)
                .indent(4)
                .build();

        this.load();

        try {
            new ConfigWatcher(path, this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            this.root = this.loader.load();
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
    }

    public int getXOffset() {
        return this.root.node("x-offset").getInt(200);
    }

    public int getYOffset() {
        return this.root.node("y-offset").getInt(26);
    }
}
