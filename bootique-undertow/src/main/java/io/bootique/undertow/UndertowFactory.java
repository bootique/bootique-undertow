package io.bootique.undertow;

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
public class UndertowFactory {
    private List<HttpListener> httpListeners;
    private List<HttpsListener> httpsListeners;
    private Integer bufferSize;
    private Integer ioThreads;
    private Integer workerThreads;
    private Boolean directBuffers;
    private StaticResourceFactory staticFiles;

    public UndertowFactory() {
        httpListeners = Collections.singletonList(new HttpListener());
        httpsListeners = new ArrayList<>();
    }

    public List<HttpListener> getHttpListeners() {
        return httpListeners;
    }

    public UndertowFactory setHttpListeners(List<HttpListener> httpListeners) {
        this.httpListeners = httpListeners;
        return this;
    }

    public List<HttpsListener> getHttpsListeners() {
        return httpsListeners;
    }

    public UndertowFactory setHttpsListeners(List<HttpsListener> httpsListeners) {
        this.httpsListeners = httpsListeners;
        return this;
    }

    public Integer getBufferSize() {
        return bufferSize;
    }

    public UndertowFactory setBufferSize(Integer bufferSize) {
        this.bufferSize = bufferSize;
        return this;
    }

    public Integer getIoThreads() {
        return ioThreads;
    }

    public UndertowFactory setIoThreads(Integer ioThreads) {
        this.ioThreads = ioThreads;
        return this;
    }

    public Integer getWorkerThreads() {
        return workerThreads;
    }

    public UndertowFactory setWorkerThreads(Integer workerThreads) {
        this.workerThreads = workerThreads;
        return this;
    }

    public Boolean getDirectBuffers() {
        return directBuffers;
    }

    public UndertowFactory setDirectBuffers(Boolean directBuffers) {
        this.directBuffers = directBuffers;
        return this;
    }

    public StaticResourceFactory getStaticFiles() {
        return staticFiles;
    }

    public UndertowFactory setStaticFiles(StaticResourceFactory staticFiles) {
        this.staticFiles = staticFiles;
        return this;
    }
}
