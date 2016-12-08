package io.bootique.undertow.config;

import io.bootique.resource.ResourceFactory;

// TODO: Check if passwords can be byte-arrays
public class HttpsListener extends HttpListener {
    private ResourceFactory keyStore;
    private String keystorePassword;
    private String keyPassword;

    public ResourceFactory getKeyStore() {
        return keyStore;
    }

    public HttpsListener setKeyStore(ResourceFactory keyStore) {
        this.keyStore = keyStore;
        return this;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public HttpsListener setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
        return this;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    public HttpsListener setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
        return this;
    }
}
