package io.bootique.undertow;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.bootique.BQCoreModule;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.log.BootLogger;
import io.bootique.shutdown.ShutdownManager;
import io.bootique.undertow.command.ServerCommand;
import io.bootique.undertow.handlers.DefaultHandler;
import io.bootique.undertow.handlers.RootHandler;
import io.undertow.server.HttpHandler;

import javax.net.ssl.SSLContext;

import static io.undertow.Undertow.Builder;
import static io.undertow.Undertow.builder;

/**
 * @since 0.1
 */
public class UndertowModule extends ConfigModule {

    @Override
    public void configure(Binder binder) {
        BQCoreModule.extend(binder)
                .addCommand(ServerCommand.class);

        binder.bind(HttpHandler.class)
                .annotatedWith(RootHandler.class)
                .to(DefaultHandler.class)
                .in(Singleton.class);
    }

    @Provides
    @Singleton
    public UndertowFactory undertowFactory(ConfigurationFactory configurationFactory) {
        return configurationFactory.config(UndertowFactory.class, configPrefix);
    }

    @Provides
    @Singleton
    public UndertowServer createServer(
            Builder builder,
            BootLogger bootLogger,
            ShutdownManager shutdownManager
    ) {
        UndertowServer server = new UndertowServer(builder);

        shutdownManager.addShutdownHook(() -> {
            bootLogger.trace(() -> "stopping Undertow...");
            server.stop();
        });

        return server;
    }

    @Singleton
    @Provides
    public Builder createBuilder(
            UndertowFactory undertowFactory,
            @RootHandler HttpHandler rootHandler
    ) {
        final Builder builder = builder();

        undertowFactory.getHttpListeners()
                .forEach(listener ->
                        builder.addHttpListener(
                                listener.getPort(),
                                listener.getHost()
                        )
                );

        undertowFactory.getHttpsListeners()
                .forEach(listener ->
                        builder.addHttpsListener(
                                listener.getPort(),
                                listener.getHost(),
                                createSslContext(undertowFactory)
                        )
                );

        if (undertowFactory.getBufferSize() != null) {
            builder.setBufferSize(undertowFactory.getBufferSize());
        }

        if (undertowFactory.getIoThreads() != null) {
            builder.setIoThreads(undertowFactory.getIoThreads());
        }

        if (undertowFactory.getWorkerThreads() != null) {
            builder.setWorkerThreads(undertowFactory.getWorkerThreads());
        }

        if (undertowFactory.getDirectBuffers() != null) {
            builder.setDirectBuffers(undertowFactory.getDirectBuffers());
        }

        builder.setHandler(rootHandler);

        return builder;
    }

    private SSLContext createSslContext(UndertowFactory config) {
        return null;
    }
}
