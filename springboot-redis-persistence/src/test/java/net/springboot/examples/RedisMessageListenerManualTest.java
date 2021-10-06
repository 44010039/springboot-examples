package net.springboot.examples;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.springboot.examples.queue.RedisMessagePublisher;
import net.springboot.examples.queue.RedisMessageSubscriber;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RedisPersistenceApplication.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class RedisMessageListenerManualTest {
    @Autowired
    private RedisMessagePublisher redisMessagePublisher;

    @Test
    public void testOnMessage() throws Exception {
        String message = "Message " + UUID.randomUUID();
        redisMessagePublisher.publish(message);
        Thread.sleep(1000);
        assertTrue(RedisMessageSubscriber.messageList.get(0).contains(message));
    }
}
