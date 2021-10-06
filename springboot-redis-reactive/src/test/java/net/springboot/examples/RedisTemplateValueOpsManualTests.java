package net.springboot.examples;

import java.time.Duration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RedisReactiveApplication.class)
public class RedisTemplateValueOpsManualTests {
    @Autowired
    private ReactiveRedisTemplate<String, Employee> employeeTemplate;

    private ReactiveValueOperations<String, Employee> employeeOperation;

    @BeforeAll
    public void setup() {
        employeeOperation = employeeTemplate.opsForValue();
    }

    @Test
    public void givenEmployee_whenSet_thenSet() {

        Mono<Boolean> result = employeeOperation.set("123", new Employee("123", "Bill", "Accounts"));

        StepVerifier.create(result).expectNext(true).verifyComplete();
    }

    // @Test
    // public void givenEmployeeId_whenGet_thenReturnsEmployee() {
    // Mono<Employee> fetchedEmployee = employeeOperation.get("123");
    // StepVerifier.create(fetchedEmployee)
    // .expectNext(new Employee("123", "Bill", "Accounts"))
    // .verifyComplete();
    // }

    @Test
    public void givenEmployee_whenSetWithExpiry_thenSetsWithExpiryTime() throws InterruptedException {

        Mono<Boolean> result = employeeOperation
                .set("129", new Employee("129", "John", "Programming"), Duration.ofSeconds(1));

        Mono<Employee> fetchedEmployee = employeeOperation.get("129");

        StepVerifier.create(result).expectNext(true).verifyComplete();

        Thread.sleep(2000L);

        StepVerifier.create(fetchedEmployee).expectNextCount(0L).verifyComplete();
    }
}
