package io.bootique.undertow.handlers;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

/**
 * Default implementation that bound to {@link RootHandler}.
 *
 * @author Ibragimov Ruslan
 * @since 0.25
 */
public class DefaultHandler implements HttpHandler {
    @Override
    public void handleRequest(HttpServerExchange exchange) {
    }
}
