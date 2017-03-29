package io.bootique.undertow;

import io.bootique.annotation.BQConfig;
import io.bootique.annotation.BQConfigProperty;
import io.bootique.resource.FolderResourceFactory;
import io.undertow.server.handlers.resource.PathResourceManager;
import io.undertow.server.handlers.resource.ResourceManager;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents settings for static files serving.
 *
 * @since 0.1
 */
@BQConfig
public class StaticResourceFactory {
    /**
     * Path of folder with files.
     */
    private FolderResourceFactory path;
    /**
     * Url for publishing files.
     */
    private String url;
    /**
     * Cache files in memory.
     */
    private boolean cache;

    public ResourceManager getResourceManager() {
        final Path folder = Paths.get(path.getUrl().getPath());
        return new PathResourceManager(folder, 10000L);
    }

    public FolderResourceFactory getPath() {
        return path;
    }

    @BQConfigProperty
    public StaticResourceFactory setPath(FolderResourceFactory path) {
        this.path = path;
        return this;
    }

    public String getUrl() {
        return url;
    }

    @BQConfigProperty
    public StaticResourceFactory setUrl(String url) {
        this.url = url;
        return this;
    }

    public boolean isCache() {
        return cache;
    }

    @BQConfigProperty
    public StaticResourceFactory setCache(boolean cache) {
        this.cache = cache;
        return this;
    }
}
