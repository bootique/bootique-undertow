package io.bootique.undertow;

import com.google.inject.Binder;
import com.google.inject.multibindings.Multibinder;
import io.bootique.ModuleExtender;
import io.bootique.undertow.handlers.Controller;
import io.bootique.undertow.handlers.OrderedHandlerWrapper;
import io.undertow.server.HandlerWrapper;
import io.undertow.server.HttpHandler;

/**
 * @since 0.2
 */
public class UndertowModuleExtender extends ModuleExtender<UndertowModuleExtender> {

    public UndertowModuleExtender(Binder binder) {
        super(binder);
    }

    @Override
    public UndertowModuleExtender initAllExtensions() {
        contributeControllers();
        contributeHandlers();
        contributeWrappers();
        contributeOrderedWrappers();
        return this;
    }

    public UndertowModuleExtender addHandler(Class<? extends HttpHandler> handlerType) {
        contributeHandlers().addBinding().to(handlerType);
        return this;
    }

    public UndertowModuleExtender addController(Class<? extends Controller> controllerType) {
        contributeControllers().addBinding().to(controllerType);
        return this;
    }

    @Deprecated
    public UndertowModuleExtender addWrapper(Class<? extends HandlerWrapper> wrapperType) {
        contributeWrappers().addBinding().to(wrapperType);
        return this;
    }

    public UndertowModuleExtender addOrderedWrapper(Class<? extends OrderedHandlerWrapper> orderedWrapperType) {
        contributeOrderedWrappers().addBinding().to(orderedWrapperType);
        return this;
    }

    protected Multibinder<HttpHandler> contributeHandlers() {
        return Multibinder.newSetBinder(binder, HttpHandler.class);
    }

    protected Multibinder<Controller> contributeControllers() {
        return Multibinder.newSetBinder(binder, Controller.class);
    }

    @Deprecated
    protected Multibinder<HandlerWrapper> contributeWrappers() {
        return Multibinder.newSetBinder(binder, HandlerWrapper.class);
    }

    protected Multibinder<OrderedHandlerWrapper> contributeOrderedWrappers() {
        return Multibinder.newSetBinder(binder, OrderedHandlerWrapper.class);
    }
}
