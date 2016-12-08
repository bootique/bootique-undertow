package io.bootique.undertow.test.junit;

import io.bootique.BQCoreModule;
import io.bootique.test.BQDaemonTestRuntime;
import io.bootique.test.junit.BQDaemonTestFactory;
import io.bootique.undertow.UndertowModule;
import io.bootique.undertow.UndertowServer;
import io.bootique.undertow.command.ServerCommand;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Collection;
import java.util.function.Function;

/**
 * A integrational test helper that starts a Bootique Undertow server.
 * <p>
 * <p>
 * Instances should be annotated within the unit tests with {@link Rule} or
 * {@link ClassRule}. E.g.:
 * <p>
 * <pre>
 * public class MyTest {
 *
 * 	&#64;Rule
 * 	public UndertowTestFactory testFactory = new UndertowTestFactory();
 * }
 * </pre>
 *
 * @since 0.1
 */
public class UndertowTestFactory extends BQDaemonTestFactory {
    /**
     * @return a new instance of builder for the test runtime stack.
     * @since 0.1
     */
    @Override
    public Builder app(String... args) {
        Function<BQDaemonTestRuntime, Boolean> startupCheck =
                r -> r.getRuntime().getInstance(UndertowServer.class).isStarted();

        return new Builder(runtimes, args)
                .startupCheck(startupCheck)
                .modules(UndertowModule.class);
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return super.apply(base, description);
    }


    public static class Builder extends io.bootique.test.junit.BQDaemonTestFactory.Builder<Builder> {

        protected Builder(Collection<BQDaemonTestRuntime> runtimes, String[] args) {
            super(runtimes, args);
        }

        @Override
        public BQDaemonTestRuntime start() {
            module(binder -> BQCoreModule.setDefaultCommand(binder, ServerCommand.class));
            return super.start();
        }
    }
}
