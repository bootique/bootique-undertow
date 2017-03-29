package io.bootique.undertow;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

public class UndertowModuleProvider implements BQModuleProvider {

    @Override
    public Module module() {
        return new UndertowModule();
    }

    @Override
    public Map<String, Type> configs() {
        // TODO: config prefix is hardcoded. Refactor away from ConfigModule, and make provider
        // generate config prefix, reusing it in metadata...
        return Collections.singletonMap("undertow", UndertowFactory.class);
    }
}
