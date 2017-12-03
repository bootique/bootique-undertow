package io.bootique.undertow;

import io.bootique.test.junit.BQTestFactory;
import io.bootique.undertow.handlers.Controller;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.util.Headers;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class ServerCommandIT {

    @Rule
    public BQTestFactory testFactory = new BQTestFactory().autoLoadModules();

    @Test
    public void testRun() {

        testFactory.app("-s")
                .module(b -> UndertowModule.extend(b).addController(TestController.class))
                .run();

        // testing that the server is in the operational state by the time ServerCommand exits...
        WebTarget base = ClientBuilder.newClient().target("http://localhost:8080");

        Response r = base.path("/").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        assertEquals("Hello World!", r.readEntity(String.class));
    }

    public static class TestController implements Controller {
        @Override
        public void defineRoutes(RoutingHandler routingHandler) {
            routingHandler.get("/", this::get);
        }

        public void get(HttpServerExchange exchange) {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send("Hello World!");
        }
    }
}
