[![Build Status](https://travis-ci.org/bootique/bootique-undertow.svg)](https://travis-ci.org/bootique/bootique-undertow)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.bootique.undertow/bootique-undertow/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.bootique.undertow/bootique-undertow/)

# bootique-undertow

Provides [Undertow](http://undertow.io/) integration with [Bootique](http://bootique.io).

## Goals

Fast, Testable, Simple HTTP Server, that supports DI in Controllers and Middlewares.

## Features:

1. Configure server via Bootique YAML config.
2. Contribute HandlerWrappers aka Middleware
3. Contribute Controllers
4. DI should work with Controllers and Middleware


## IDEAS:

Routing: 
```
router.GET("/", Controller::showPost).middleware(Convector::toJson, Check::checkSome);
```

```kotlin
fun route(request: ServerRequest) = RouterFunctionDsl {
    accept(TEXT_HTML).apply {
            (GET("/user/") or GET("/users/")) { findAllView() }
            GET("/user/{login}") { findViewById() }
    }
    accept(APPLICATION_JSON).apply {
            (GET("/api/user/") or GET("/api/users/")) { findAll() }
            POST("/api/user/") { create() }
            POST("/api/user/{login}") { findOne() }
    }
 } (request)
 ```
