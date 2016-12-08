package io.bootique.undertow;

import com.google.inject.Inject;
import io.bootique.undertow.handlers.Controller;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.util.Headers;

public class EchoRestController implements Controller {
    @Inject
    public EchoRestController(RoutingHandler routingHandler) {
        routingHandler
                .get("/", this::get)
                .get("/exception", this::getException)
                .post("/{name}", this::post);
    }

    public void get(HttpServerExchange exchange) {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Hello World!");
    }

    public void getException(HttpServerExchange exchange) {
        throw new RuntimeException("Programmatic Error");
    }

    public void post(HttpServerExchange exchange) {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send("Hello " + exchange.getQueryParameters().get("name").getFirst() + "!");
    }
}
