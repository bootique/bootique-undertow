package io.bootique.undertow;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

public class UndertowModuleProvider implements BQModuleProvider {

    @Override
    public Module module() {
        return new UndertowModule();
    }
}
