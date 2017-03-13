package io.bootique.undertow;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.Bootique;

import static io.bootique.undertow.UndertowModule.contributeControllers;
import static io.bootique.undertow.UndertowModule.contributeWrappers;

public class Application implements Module {
    public static void main(String[] args) {
        Bootique.app(args)
                .module(UndertowModule.class)
                .module(Application.class)
                .run();
    }

    @Override
    public void configure(Binder binder) {
        contributeControllers(binder)
                .addBinding()
                .to(EchoRestController.class);

        contributeWrappers(binder)
                .addBinding()
                .to(ApplicationExceptionHandler.class);
    }
}
