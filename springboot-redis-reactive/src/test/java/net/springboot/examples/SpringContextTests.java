package net.springboot.examples;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RedisReactiveApplication.class)
public class SpringContextTests {
    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }    
}
