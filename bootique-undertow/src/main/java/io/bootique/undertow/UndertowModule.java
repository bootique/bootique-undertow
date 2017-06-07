package io.bootique.undertow;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import io.bootique.BQCoreModule;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.undertow.command.ServerCommand;
import io.bootique.undertow.handlers.Controller;
import io.bootique.undertow.handlers.OrderedHandlerWrapper;
import io.undertow.Handlers;
import io.undertow.server.HandlerWrapper;
import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.ResourceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.util.List;
import java.util.Set;

import static io.undertow.Handlers.path;
import static io.undertow.Undertow.Builder;
import static io.undertow.Undertow.builder;

/**
 * @since 0.1
 */
public class UndertowModule extends ConfigModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(UndertowModule.class);

    /**
     * @param binder Guice binder.
     * @return an extender object to customize {@link UndertowModule}.
     * @since 0.2
     */
    public static UndertowModuleExtender extend(Binder binder) {
        return new UndertowModuleExtender(binder);
    }

    /**
     * @param binder Guice binder.
     * @return handlers multibinder
     * @deprecated since 0.2 in favor of {@link #extend(Binder)} method.
     */
    @Deprecated
    public static Multibinder<HttpHandler> contributeHandlers(Binder binder) {
        return Multibinder.newSetBinder(binder, HttpHandler.class);
    }

    /**
     * @param binder Guice binder.
     * @return controllers multibinder
     * @deprecated since 0.2 in favor of {@link #extend(Binder)} method.
     */
    @Deprecated
    public static Multibinder<Controller> contributeControllers(Binder binder) {
        return Multibinder.newSetBinder(binder, Controller.class);
    }

    /**
     * @param binder Guice binder.
     * @return handler wrappers multibinder
     * @deprecated since 0.2 in favor of {@link #extend(Binder)} method.
     */
    @Deprecated
    public static Multibinder<HandlerWrapper> contributeWrappers(Binder binder) {
        return Multibinder.newSetBinder(binder, HandlerWrapper.class);
    }

    @Override
    public void configure(Binder binder) {
        BQCoreModule.extend(binder).addCommand(ServerCommand.class);
        UndertowModule.extend(binder).initAllExtensions();
    }

    @Provides
    @Singleton
    public UndertowServer createServer(Builder builder) {
        return new UndertowServer(builder);
    }

    @Provides
    @Singleton
    public RoutingHandler createRouter() {
        return Handlers.routing();
    }

    @Singleton
    @Provides
    public Builder createBuilder(ConfigurationFactory configFactory,
                                 RoutingHandler routingHandler,
                                 Set<HandlerWrapper> handlerWrappers,
                                 Set<OrderedHandlerWrapper> orderedHandlerWrappers,
                                 Set<Controller> controllers) {
        final Builder builder = builder();
        final UndertowFactory config = configFactory.config(UndertowFactory.class, configPrefix);

        config.getHttpListeners().forEach(listener -> {
            builder.addHttpListener(listener.getPort(), listener.getHost());
        });

        config.getHttpsListeners().forEach(listener -> {
            builder.addHttpsListener(listener.getPort(), listener.getHost(), createSslContext(config));
        });

        if (config.getBufferSize() != null) {
            builder.setBufferSize(config.getBufferSize());
        }

        if (config.getIoThreads() != null) {
            builder.setIoThreads(config.getIoThreads());
        }

        if (config.getWorkerThreads() != null) {
            builder.setWorkerThreads(config.getWorkerThreads());
        }

        if (config.getDirectBuffers() != null) {
            builder.setDirectBuffers(config.getDirectBuffers());
        }

        HttpHandler root = routingHandler;

        if (handlerWrappers != null) {
            for (HandlerWrapper wrapper : handlerWrappers) {
                root = wrapper.wrap(root);
            }
        }

        if (orderedHandlerWrappers != null) {
            final List<OrderedHandlerWrapper> orderedWrappers = UndertowModuleUtils.sortWrappers(orderedHandlerWrappers);

            for (OrderedHandlerWrapper orderedWrapper : orderedWrappers) {
                root = orderedWrapper.wrap(root);
            }
        }

        final PathHandler pathHandler = path();

        controllers.forEach(controller -> {
            controller.defineRoutes(routingHandler);
            LOGGER.info("Controller '{}' registered.", controller.getClass().getSimpleName());
        });


        if (config.getStaticFiles().size() > 0) {
            for (StaticResourceFactory filesConfig : config.getStaticFiles()) {
                final String url = filesConfig.getUrl();
                if ("/".equals(filesConfig.getUrl())) {
                    if (routingHandler.getFallbackHandler() != null) {
                        routingHandler.setFallbackHandler(new ResourceHandler(filesConfig.getResourceManager(), routingHandler.getFallbackHandler()));
                    }
                } else {
                    final ResourceHandler resourceHandler = new ResourceHandler(filesConfig.getResourceManager());
                    pathHandler.addPrefixPath(url, resourceHandler);
                }
            }
        }

        pathHandler.addPrefixPath("/", root);

        builder.setHandler(pathHandler);


        // UndertowOptions;

        return builder;
    }

    private SSLContext createSslContext(UndertowFactory config) {
        return null;
    }
}
