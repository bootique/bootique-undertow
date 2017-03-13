package io.bootique.undertow.handlers;

import io.undertow.server.RoutingHandler;

/**
 * Base Interface for all Undertow Controllers.
 *
 * @since 0.1
 */
public interface Controller {

    /**
     * Allow controller define mapping.
     *
     * @param routingHandler router which handles routing via path template and method name.
     */
    void defineRoutes(RoutingHandler routingHandler);
}
