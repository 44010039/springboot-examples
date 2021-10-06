package net.springboot.examples;

import com.github.sonus21.rqueue.producer.RqueueMessageSender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailPublishService {
    @Value("${redis.queue.email.name}")
    private String name;

    private final RqueueMessageSender rqueueMessageSender;

    public EmailPublishService(RqueueMessageSender rqueueMessageSender) {
        this.rqueueMessageSender = rqueueMessageSender;
    }

    public void sendEmail(String email, String subject, String content) {
        log.info("Sending email");
        rqueueMessageSender.put(name,
                new Email(email, subject, content));

    }
}
