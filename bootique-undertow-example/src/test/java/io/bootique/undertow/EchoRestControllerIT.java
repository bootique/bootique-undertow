package io.bootique.undertow;

import io.bootique.undertow.test.junit.UndertowTestFactory;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EchoRestControllerIT {
    @Rule
    public UndertowTestFactory testFactory = new UndertowTestFactory();

    @Before
    public void testRuntime() {
        testFactory
                .app("--config=classpath:static.yml")
                .module(Application.class)
                .autoLoadModules()
                .start();
    }

    @Test
    public void handleGetRequest() throws Exception {
        final Content content = Request.Get("http://localhost:8080/")
                .execute()
                .returnContent();

        assertEquals("Hello World!", content.asString());
    }

    @Test
    public void handlePostRequest() throws Exception {
        final Content content = Request.Post("http://localhost:8080/Vader")
                .execute()
                .returnContent();

        assertEquals("Hello Vader!", content.asString());
    }

    @Test
    public void handleStatic() throws Exception {
        final Content content = Request.Get("http://localhost:8080/static/test.txt")
                .execute()
                .returnContent();

        assertEquals("Content", content.asString());
    }

    @Test
    public void handleException() throws Exception {
        final Content content = Request.Get("http://localhost:8080/exception")
                .execute()
                .returnContent();

        assertEquals("Programmatic Error", content.asString());
    }
}