package io.bootique.undertow.handlers;

import io.undertow.server.HandlerWrapper;

/**
 * Base Interface for HandlerWrappers which supports ordering.
 *
 * @author Ibragimov Ruslan
 * @since 0.4
 */
public interface OrderedHandlerWrapper extends HandlerWrapper {

    /**
     * @return order of wrapper. Wrappers with higher number will evaluated first.
     */
    int getOrder();
}
