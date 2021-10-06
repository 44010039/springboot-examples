package net.springboot.examples.chapter01;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("article")
public class Article {
    private Long id;
    private String title;
    private String user;
    private String link;
}
