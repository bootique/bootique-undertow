package io.bootique.undertow;

import io.bootique.test.junit.BQModuleProviderChecker;
import org.junit.Test;

public class UndertowModuleProviderTest {

    @Test
    public void testPresentInJar() {
        BQModuleProviderChecker.testPresentInJar(UndertowModuleProvider.class);

    }

    @Test
    public void testMetadata() {
        BQModuleProviderChecker.testMetadata(UndertowModuleProvider.class);
    }
}
