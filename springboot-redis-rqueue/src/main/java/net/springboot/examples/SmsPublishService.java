package net.springboot.examples;

import com.github.sonus21.rqueue.producer.RqueueMessageSender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsPublishService {
    @Value("${redis.queue.sms.name}")
    private String name;
    @Value("${redis.queue.sms.delay}")
    private Long delay;

    private final RqueueMessageSender rqueueMessageSender;

    public SmsPublishService(RqueueMessageSender rqueueMessageSender) {
        this.rqueueMessageSender = rqueueMessageSender;
    }

    public void sendSms(String phone, String message) {
        log.info("Sending sms");
        rqueueMessageSender.put(name,
                new Sms(phone, message), delay);

    }
}
