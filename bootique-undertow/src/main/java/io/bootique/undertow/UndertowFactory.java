/**
 *    Licensed to ObjectStyle LLC under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ObjectStyle LLC licenses
 *  this file to you under the Apache License, Version 2.0 (the
 *  “License”); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  “AS IS” BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

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

    public UndertowFactory() {
        httpListeners = Collections.singletonList(new HttpListener());
        httpsListeners = new ArrayList<>();
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
}
