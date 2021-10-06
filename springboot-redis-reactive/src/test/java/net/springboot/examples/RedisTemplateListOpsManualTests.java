package net.springboot.examples;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveListOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RedisReactiveApplication.class)
public class RedisTemplateListOpsManualTests {
    private static final String LIST_NAME = "demo_list";

    @Autowired
    private ReactiveStringRedisTemplate redisTemplate;

    private ReactiveListOperations<String, String> reactiveListOps;
    

    @BeforeAll
    public void setup() {
        reactiveListOps = redisTemplate.opsForList();
    }

    @Test
    public void givenListAndValues_whenLeftPushAndLeftPop_thenLeftPushAndLeftPop() {
        Mono<Long> lPush = reactiveListOps.leftPushAll(LIST_NAME, "first", "second")
            .log("Pushed");

        StepVerifier.create(lPush)
            .expectNext(2L)
            .verifyComplete();

        Mono<String> lPop = reactiveListOps.leftPop(LIST_NAME)
            .log("Popped");

        StepVerifier.create(lPop)
            .expectNext("second")
            .verifyComplete();
    } 
}
