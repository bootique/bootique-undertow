package io.bootique.undertow.config;

import io.bootique.annotation.BQConfigProperty;
import io.bootique.resource.ResourceFactory;

// TODO: Check if passwords can be byte-arrays
public class HttpsListener extends HttpListener {
    private ResourceFactory keyStore;
    private String keystorePassword;
    private String keyPassword;

    public ResourceFactory getKeyStore() {
        return keyStore;
    }

    @BQConfigProperty
    public HttpsListener setKeyStore(ResourceFactory keyStore) {
        this.keyStore = keyStore;
        return this;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    @BQConfigProperty
    public HttpsListener setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
        return this;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    @BQConfigProperty
    public HttpsListener setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
        return this;
    }
}
