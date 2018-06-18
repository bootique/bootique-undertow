/**
 *  Licensed to ObjectStyle LLC under one
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

package io.bootique.undertow.config;

import io.bootique.annotation.BQConfigProperty;
import io.bootique.resource.ResourceFactory;

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
