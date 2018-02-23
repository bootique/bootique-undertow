package io.bootique.undertow.handlers;

import io.undertow.server.HttpHandler;

/**
 * Handler which will be contributed to undertow.
 *
 * @author Ibragimov Ruslan
 * @since 0.25
 */
public interface RootHandlerProvider {
    HttpHandler handler();

    static RootHandlerProvider of(HttpHandler handler) {
        return new DefaultRootHandlerProvider(handler);
    }

    class DefaultRootHandlerProvider implements RootHandlerProvider {

        private final HttpHandler handler;

        DefaultRootHandlerProvider(HttpHandler handler) {
            this.handler = handler;
        }

        @Override
        public HttpHandler handler() {
            return handler;
        }
    }
}
