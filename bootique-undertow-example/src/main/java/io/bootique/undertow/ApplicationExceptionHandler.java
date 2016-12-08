package io.bootique.undertow;

import io.undertow.server.HandlerWrapper;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationExceptionHandler implements HandlerWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @Override
    public HttpHandler wrap(HttpHandler handler) {
        final ExceptionHandler exceptionHandler = new ExceptionHandler(handler);
        exceptionHandler.addExceptionHandler(RuntimeException.class, exchange -> {
            final Throwable throwable = exchange.getAttachment(ExceptionHandler.THROWABLE);
            LOGGER.error("Runtime Error occurred.", throwable);
            exchange.getResponseSender().send(throwable.getMessage());
        });
        return exceptionHandler;
    }
}
