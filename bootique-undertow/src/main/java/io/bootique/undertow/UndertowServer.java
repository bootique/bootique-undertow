package io.bootique.undertow;

import io.undertow.Undertow;
import io.undertow.Undertow.Builder;

/**
 * Bootique wrapper around undertow for managing state of server.
 *
 * @since 0.1
 */
public class UndertowServer {

    private final Undertow undertow;
    private boolean started = false;

    public UndertowServer(Builder builder) {
        this.undertow = builder.build();
    }

    public void start() {
        this.undertow.start();
        this.started = true;
    }

    public boolean isStarted() {
        return started;
    }

    public void stop() {
        if (this.started) {
            this.started = false;
            this.undertow.stop();
        }
    }
}
