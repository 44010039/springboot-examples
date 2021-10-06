package net.springboot.examples;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisTemplateConfig {
    @Bean
    public RedisTemplate<String, Item> redisTemplate(RedisConnectionFactory cf){
      RedisTemplate<String,Item> redisTemplate = new RedisTemplate<String, Item>();
      redisTemplate.setConnectionFactory(cf);
      return redisTemplate;
    }    
}
