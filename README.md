<!--
  Licensed to ObjectStyle LLC under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ObjectStyle LLC licenses
  this file to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
  -->
[![build test deploy](https://github.com/bootique/bootique-undertow/actions/workflows/maven.yml/badge.svg)](https://github.com/bootique/bootique-undertow/actions/workflows/maven.yml)
[![Maven Central](https://img.shields.io/maven-central/v/io.bootique.undertow/bootique-undertow.svg?colorB=brightgreen)](https://search.maven.org/artifact/io.bootique.undertow/bootique-undertow/)

# bootique-undertow

**WARNING: the last release of Bootique that supports "bootique-undertow" is 3.0. It is currently deprecated, and will be removed from Bootique 4.0. We recommend our users to switch to Jetty, or to fork this module and support it on their own.**

Provides [Undertow](http://undertow.io/) integration with [Bootique](http://bootique.io).
See usage example [bootique-undertow-demo](https://github.com/bootique-examples/bootique-undertow-demo).

## Goals

Fast, Testable, Simple HTTP Server, that supports DI in Controllers and Middlewares.
