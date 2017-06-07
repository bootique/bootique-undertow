package io.bootique.undertow;

import io.bootique.undertow.handlers.OrderedHandlerWrapper;
import io.undertow.server.HttpHandler;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Test order of wrappers.
 *
 * @author Ibragimov Ruslan
 * @since 0.4
 */
public class OrderedWrappersTest {
    private static final TestOrderedWrapper w1 = new TestOrderedWrapper(1);
    private static final TestOrderedWrapper w11 = new TestOrderedWrapper(1);
    private static final TestOrderedWrapper w2 = new TestOrderedWrapper(2);
    private static final TestOrderedWrapper w22 = new TestOrderedWrapper(2);
    private static final TestOrderedWrapper w3 = new TestOrderedWrapper(3);
    private static final TestOrderedWrapper w33 = new TestOrderedWrapper(3);

    @Test
    public void sequentialOrder() throws Exception {
        Set<OrderedHandlerWrapper> wrappers = new HashSet<>(asList(w2, w1, w3));

        final List<OrderedHandlerWrapper> orderedHandlerWrappers = UndertowModuleUtils.sortWrappers(wrappers);

        assertEquals(1, orderedHandlerWrappers.get(0).getOrder());
        assertEquals(2, orderedHandlerWrappers.get(1).getOrder());
        assertEquals(3, orderedHandlerWrappers.get(2).getOrder());
    }

    @Test
    public void sameOrder() throws Exception {
        Set<OrderedHandlerWrapper> wrappers = new HashSet<>(asList(w1, w11, w2, w3, w33));

        final List<OrderedHandlerWrapper> orderedHandlerWrappers = UndertowModuleUtils.sortWrappers(wrappers);

        assertEquals(1, orderedHandlerWrappers.get(0).getOrder());
        assertEquals(1, orderedHandlerWrappers.get(1).getOrder());
        assertEquals(2, orderedHandlerWrappers.get(2).getOrder());
        assertEquals(3, orderedHandlerWrappers.get(3).getOrder());
        assertEquals(3, orderedHandlerWrappers.get(4).getOrder());
    }

    static final class TestOrderedWrapper implements OrderedHandlerWrapper {

        private final int order;

        TestOrderedWrapper(int order) {
            this.order = order;
        }

        @Override
        public int getOrder() {
            return order;
        }

        @Override
        public HttpHandler wrap(HttpHandler httpHandler) {
            return httpHandler;
        }
    }
}


