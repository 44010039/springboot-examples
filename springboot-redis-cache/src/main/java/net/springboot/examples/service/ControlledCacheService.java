package net.springboot.examples.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ControlledCacheService {
    private static final String CONTROLLED_PREFIX = "myControlledPrefix_";

    public static String getCacheKey(String relevant){
        return CONTROLLED_PREFIX + relevant;
    }

    @Cacheable(cacheNames = "myControlledCache", key = "T(net.springboot.examples.service.ControlledCacheService).getCacheKey(#relevant)")
    public String getFromCache(String relevant) {
        return null;
    }

    @CachePut(cacheNames = "myControlledCache", key = "T(net.springboot.examples.service.ControlledCacheService).getCacheKey(#relevant)")
    public String populateCache(String relevant, String unrelevantTrackingId) {
        return "Hello " + relevant;
    }

    @CacheEvict(cacheNames = "myControlledCache", key = "T(net.springboot.examples.service.ControlledCacheService).getCacheKey(#relevant)")
    public void removeFromCache(String relevant) {
        log.info("Forgetting everything about this '{}'!", relevant);
    }
}
