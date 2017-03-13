package io.bootique.undertow.command;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;
import io.bootique.undertow.UndertowServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command for running Undertow server.
 *
 * @since 0.1
 */
public class ServerCommand extends CommandWithMetadata {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerCommand.class);

    private final Provider<UndertowServer> serverProvider;

    @Inject
    public ServerCommand(Provider<UndertowServer> serverProvider) {
        super(createMetadata());
        this.serverProvider = serverProvider;
    }

    @Override
    public CommandOutcome run(Cli cli) {
        LOGGER.info("Will run Undertow Server...");

        UndertowServer server = serverProvider.get();

        try {
            server.start();
        } catch (Exception e) {
            return CommandOutcome.failed(1, e);
        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException ie) {
            try {
                server.stop();
            } catch (Exception se) {
                return CommandOutcome.failed(1, se);
            }
        }

        return CommandOutcome.succeeded();
    }

    private static CommandMetadata createMetadata() {
        return CommandMetadata
                .builder(ServerCommand.class)
                .description("Starts Undertow server.")
                .build();
    }
}
