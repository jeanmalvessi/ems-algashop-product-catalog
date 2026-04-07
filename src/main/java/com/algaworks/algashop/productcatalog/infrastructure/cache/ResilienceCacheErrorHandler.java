package com.algaworks.algashop.productcatalog.infrastructure.cache;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "redis")
public class ResilienceCacheErrorHandler implements CacheErrorHandler {

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        logWarn(exception, cache, key, "GET");
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, @Nullable Object value) {
        logWarn(exception, cache, key, "PUT");
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        logWarn(exception, cache, key, "EVICT");
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        logWarn(exception, cache, "", "CLEAR");
    }

    private void logWarn(RuntimeException exception, Cache cache, Object key, String method) {
        log.warn("Cache {} error | cache='{}' | key = '{}' | cause='{}'",
                method,
                cache.getName(),
                key,
                exception.getClass().getSimpleName()
        );
    }
}
