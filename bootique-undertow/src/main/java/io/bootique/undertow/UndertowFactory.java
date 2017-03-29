package io.bootique.undertow;

import io.bootique.annotation.BQConfig;
import io.bootique.annotation.BQConfigProperty;
import io.bootique.undertow.config.HttpListener;
import io.bootique.undertow.config.HttpsListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents settings for of Undertow server.
 *
 * @since 0.1
 */
@BQConfig
public class UndertowFactory {
    private List<HttpListener> httpListeners;
    private List<HttpsListener> httpsListeners;
    private Integer bufferSize;
    private Integer ioThreads;
    private Integer workerThreads;
    private Boolean directBuffers;
    private List<StaticResourceFactory> staticFiles;

    public UndertowFactory() {
        httpListeners = Collections.singletonList(new HttpListener());
        httpsListeners = new ArrayList<>();
        staticFiles = new ArrayList<>();
    }

    public List<HttpListener> getHttpListeners() {
        return httpListeners;
    }

    @BQConfigProperty
    public UndertowFactory setHttpListeners(List<HttpListener> httpListeners) {
        this.httpListeners = httpListeners;
        return this;
    }

    public List<HttpsListener> getHttpsListeners() {
        return httpsListeners;
    }

    @BQConfigProperty
    public UndertowFactory setHttpsListeners(List<HttpsListener> httpsListeners) {
        this.httpsListeners = httpsListeners;
        return this;
    }

    public Integer getBufferSize() {
        return bufferSize;
    }

    @BQConfigProperty
    public UndertowFactory setBufferSize(Integer bufferSize) {
        this.bufferSize = bufferSize;
        return this;
    }

    public Integer getIoThreads() {
        return ioThreads;
    }

    @BQConfigProperty
    public UndertowFactory setIoThreads(Integer ioThreads) {
        this.ioThreads = ioThreads;
        return this;
    }

    public Integer getWorkerThreads() {
        return workerThreads;
    }

    @BQConfigProperty
    public UndertowFactory setWorkerThreads(Integer workerThreads) {
        this.workerThreads = workerThreads;
        return this;
    }

    public Boolean getDirectBuffers() {
        return directBuffers;
    }

    @BQConfigProperty
    public UndertowFactory setDirectBuffers(Boolean directBuffers) {
        this.directBuffers = directBuffers;
        return this;
    }

    public List<StaticResourceFactory> getStaticFiles() {
        return staticFiles;
    }

    @BQConfigProperty
    public UndertowFactory setStaticFiles(List<StaticResourceFactory> staticFiles) {
        this.staticFiles = staticFiles;
        return this;
    }
}
