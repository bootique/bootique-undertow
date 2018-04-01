package io.bootique.undertow;

import io.bootique.command.CommandOutcome;
import io.bootique.test.junit.BQTestFactory;
import io.bootique.undertow.handlers.RootHandler;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.StatusCodes;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerCommandIT {

    @Rule
    public BQTestFactory testFactory = new BQTestFactory().autoLoadModules();

    @Test
    public void testRun() throws IOException {
        CommandOutcome outcome = testFactory.app("--server")
            .override(UndertowModule.class)
            .with(
                b -> b.bind(HttpHandler.class)
                    .annotatedWith(RootHandler.class)
                    .to(TestHandler.class)
            )
            .run();

        assertTrue(outcome.isSuccess());
        assertTrue(outcome.forkedToBackground());

        // testing that the server is in the operational state by the time ServerCommand exits...
        final HttpResponse response = Request.Get("http://localhost:8080/").execute().returnResponse();

        assertEquals(StatusCodes.OK, response.getStatusLine().getStatusCode());
        assertEquals("Hello World!", EntityUtils.toString(response.getEntity()));
    }

    public static class TestHandler implements HttpHandler {

        @Override
        public void handleRequest(HttpServerExchange exchange) {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send("Hello World!");
        }
    }
}
