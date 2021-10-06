package net.springboot.examples;

import com.github.sonus21.rqueue.annotation.RqueueListener;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsReceive {
    @RqueueListener(delayedQueue = "true", value = "${redis.queue.sms.name}")
    public void sendSms(Sms sms) {
        log.info("Receiving sms {}", sms);
    }
}
