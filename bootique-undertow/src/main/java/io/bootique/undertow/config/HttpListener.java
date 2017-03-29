package io.bootique.undertow.config;

import io.bootique.annotation.BQConfigProperty;

public class HttpListener {
    private Integer port;
    private String host;

    public HttpListener() {
        this.port = 8080;
    }

    public Integer getPort() {
        return port;
    }

    @BQConfigProperty
    public HttpListener setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getHost() {
        return host;
    }

    @BQConfigProperty
    public HttpListener setHost(String host) {
        this.host = host;
        return this;
    }
}
