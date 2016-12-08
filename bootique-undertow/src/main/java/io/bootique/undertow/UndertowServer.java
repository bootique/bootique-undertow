package io.bootique.undertow;

import io.undertow.Undertow;
import io.undertow.Undertow.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bootique wrapper around undertow for managing state of server.
 *
 * @since 0.1
 */
public class UndertowServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(UndertowServer.class);

    private final Undertow undertow;
    private boolean started = false;

    public UndertowServer(Builder builder) {
        this.undertow = builder.build();
    }

    public void start() {
        this.undertow.start();
        this.started = true;
        stopAtShutdown();
    }

    public boolean isStarted() {
        return started;
    }

    public synchronized void stop() {
        if (this.started) {
            this.started = false;
            this.undertow.stop();
        }
    }

    public void stopAtShutdown() {
        LOGGER.trace("Register shutdown hook");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Shutdown Undertow server before exit...");
            this.stop();
        }));
    }
}
