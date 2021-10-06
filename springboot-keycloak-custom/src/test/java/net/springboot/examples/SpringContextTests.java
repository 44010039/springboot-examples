package net.springboot.examples;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = KeycloakCustomApplicaion.class)
public class SpringContextTests {
    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
