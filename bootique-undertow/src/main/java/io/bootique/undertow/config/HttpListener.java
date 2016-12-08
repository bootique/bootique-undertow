package io.bootique.undertow.config;

public class HttpListener {
    private Integer port;
    private String host;

    public HttpListener() {
        this.port = 8080;
    }

    public Integer getPort() {
        return port;
    }

    public HttpListener setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getHost() {
        return host;
    }

    public HttpListener setHost(String host) {
        this.host = host;
        return this;
    }
}
