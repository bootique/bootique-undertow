/*
 * Licensed to ObjectStyle LLC under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ObjectStyle LLC licenses
 * this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.bootique.undertow;

import io.undertow.Undertow;
import io.undertow.Undertow.Builder;

/**
 * Bootique wrapper around undertow for managing state of server.
 *
 * @deprecated No longer supported, the users are encouraged to switch to Jetty.
 */
@Deprecated(since = "3.0", forRemoval = true)
public class UndertowServer {

    private final Undertow undertow;
    private boolean started = false;

    public UndertowServer(Builder builder) {
        this.undertow = builder.build();
    }

    public void start() {
        this.undertow.start();
        this.started = true;
    }

    public boolean isStarted() {
        return started;
    }

    public void stop() {
        if (this.started) {
            this.started = false;
            this.undertow.stop();
        }
    }
}
