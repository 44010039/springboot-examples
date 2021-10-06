package net.springboot.examples;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisRQueueApplication implements CommandLineRunner {
    @Autowired
    private EmailPublishService emailPublishService;
    @Autowired
    private SmsPublishService smsPublishService;

    public static void main(String[] args) {
        SpringApplication.run(RedisRQueueApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0; i < 10; i++) {
            String index = String.valueOf(i);
            emailPublishService.sendEmail("email-" + index, "subject-" + index, "content-" + index);
            smsPublishService.sendSms("phone-" + index ,"message-" + index);
            TimeUnit.SECONDS.sleep(1);
        }

    }
}
