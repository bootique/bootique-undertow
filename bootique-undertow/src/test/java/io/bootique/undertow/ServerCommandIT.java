/*
 * Licensed to ObjectStyle LLC under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ObjectStyle LLC licenses
 * this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.bootique.undertow;

import io.bootique.command.CommandOutcome;
import io.bootique.junit5.BQTest;
import io.bootique.junit5.BQTestFactory;
import io.bootique.junit5.BQTestTool;
import io.bootique.undertow.handlers.RootHandler;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.StatusCodes;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@BQTest
public class ServerCommandIT {

    @BQTestTool
    final BQTestFactory testFactory = new BQTestFactory().autoLoadModules();

    @Test
    public void run() {

        // TODO: dynamic port for tests similar to what JettyTester does
        CommandOutcome outcome = testFactory.app("--server")
            .module(b -> b.bind(HttpHandler.class, RootHandler.class).to(TestHandler.class))
            .run();

        assertTrue(outcome.isSuccess());
        assertTrue(outcome.forkedToBackground());

        // testing that the server is in the operational state by the time ServerCommand exits...
        WebTarget target = ClientBuilder.newClient().target("http://localhost:8080/");
        Response response = target.request().get();

        assertEquals(StatusCodes.OK, response.getStatus());
        assertEquals("Hello World!", response.readEntity(String.class));
    }

    public static class TestHandler implements HttpHandler {

        @Override
        public void handleRequest(HttpServerExchange exchange) {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
            exchange.getResponseSender().send("Hello World!");
        }
    }
}
