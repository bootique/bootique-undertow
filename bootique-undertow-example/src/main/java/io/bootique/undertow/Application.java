package io.bootique.undertow;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.Bootique;

public class Application implements Module {
    public static void main(String[] args) {
        Bootique.app(args)
                .module(UndertowModule.class)
                .module(Application.class)
                .run();
    }

    @Override
    public void configure(Binder binder) {

        UndertowModule.extend(binder)
                .addController(EchoRestController.class)
                .addWrapper(ApplicationExceptionHandler.class);
    }
}
