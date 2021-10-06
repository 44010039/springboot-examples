package net.springboot.examples;

import com.github.sonus21.rqueue.annotation.RqueueListener;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailReceive {
    @RqueueListener(value = "${redis.queue.email.name}")
    public void sendEmail(Email email) {
        log.info("Receiving email {}", email);
    }
}
