package net.savagedev.worldtimer.config;

import java.io.IOException;
import java.nio.file.*;

public class ConfigWatcher extends Thread {
    private final WatchService service = FileSystems.getDefault().newWatchService();

    private final WorldTimerConfig config;
    private final Path path;

    public ConfigWatcher(Path path, WorldTimerConfig config) throws IOException {
        path.getParent().register(this.service, StandardWatchEventKinds.ENTRY_MODIFY);

        this.config = config;
        this.path = path;
    }

    @Override
    public void run() {
        try {
            WatchKey key;
            while ((key = this.service.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }
                    if (this.path.getFileName().equals(event.context())) {
                        this.config.load();
                    }
                }
                key.reset();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
