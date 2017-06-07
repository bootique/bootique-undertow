package io.bootique.undertow;

import io.bootique.undertow.handlers.OrderedHandlerWrapper;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utils for Undertow Modules.
 *
 * @author Ibragimov Ruslan
 * @since 0.4
 */
final class UndertowModuleUtils {
    private UndertowModuleUtils() {
    }

    static List<OrderedHandlerWrapper> sortWrappers(Collection<OrderedHandlerWrapper> orderedHandlerWrappers) {
        return orderedHandlerWrappers.stream()
            .sorted(Comparator.comparingInt(OrderedHandlerWrapper::getOrder))
            .collect(Collectors.toList());
    }
}
