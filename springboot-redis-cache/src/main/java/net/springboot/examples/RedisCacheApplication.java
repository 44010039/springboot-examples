package net.springboot.examples;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import lombok.extern.slf4j.Slf4j;
import net.springboot.examples.service.CacheService;
import net.springboot.examples.service.ControlledCacheService;

@EnableCaching
@SpringBootApplication
@Slf4j
public class RedisCacheApplication implements CommandLineRunner {
    private final CacheService cacheService;
    private final ControlledCacheService controlledCacheService;

    public RedisCacheApplication(CacheService cacheService, ControlledCacheService controlledCacheService) {
        this.cacheService = cacheService;
        this.controlledCacheService = controlledCacheService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisCacheApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        String firstString = cacheService.cacheThis("param1", UUID.randomUUID().toString());
        log.info("First: {}", firstString);
        String secondString = cacheService.cacheThis("param1", UUID.randomUUID().toString());
        log.info("Second: {}", secondString);
        String thirdString = cacheService.cacheThis("AnotherParam", UUID.randomUUID().toString());
        log.info("Third: {}", thirdString);
        String fourthString = cacheService.cacheThis("AnotherParam", UUID.randomUUID().toString());
        log.info("Fourth: {}", fourthString);

        log.info("Starting controlled cache: -----------");
        String controlledFirst = getFromControlledCache("first");
        log.info("Controlled First: {}", controlledFirst);
        String controlledSecond = getFromControlledCache("second");
        log.info("Controlled Second: {}", controlledSecond);

        getFromControlledCache("first");
        getFromControlledCache("second");
        getFromControlledCache("third");

        // log.info("Clearing all cache entries:");
        // cacheService.forgetAboutThis("param1");
        // cacheService.forgetAboutThis("AnotherParam");
        // controlledCacheService.removeFromCache("first");
        // controlledCacheService.removeFromCache("second");
        // controlledCacheService.removeFromCache("third");
        
    }
    

    private String getFromControlledCache(String param) {
        String fromCache = controlledCacheService.getFromCache(param);
        if (fromCache == null) {
            log.info("Oups - Cache '{}' was empty. Going to populate it", param);
            String newValue = controlledCacheService.populateCache(param, UUID.randomUUID().toString());
            log.info("Populated Cache with: {}", newValue);
            return newValue;
        }
        log.info("Returning from Cache: {}", fromCache);
        return fromCache;
    }
    
}
