package net.springboot.examples;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.TestConfiguration;

import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;


@Slf4j
@TestConfiguration
public class RedisTestConfiguration {
    private RedisServer redisServer;

    public RedisTestConfiguration(RedisProperties redisProperties) {
        int port = redisProperties.getPort();
        this.redisServer = new RedisServerBuilder().port(port).build();
        log.info("Embedded Redis[port:{}]", port);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("Embedded redis server start...");
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Embedded redis server stop...");
        redisServer.stop();
    }   
}
