package net.springboot.examples;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;


@Slf4j
@Configuration
public class RedisTestConfiguration{    
    private RedisServer redisServer;

    public RedisTestConfiguration(RedisProperties redisProperties) {
        int port = redisProperties.getPort();
        log.info("Embedded Redis[port:{}]", port);
        this.redisServer = new RedisServerBuilder().port(port).build();
    }

    @PostConstruct
    public void postConstruct() {
        log.info("Embedded Redis Start...");
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Embedded Redis Stop...");
        redisServer.stop();
    } 
}
