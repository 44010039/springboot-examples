package net.springboot.examples;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class EmployeeController {
    private final ReactiveRedisTemplate<String, Employee> employeeTemplate;

    public EmployeeController(ReactiveRedisTemplate<String, Employee> employeeTemplate) {
        this.employeeTemplate = employeeTemplate;
    }

    @GetMapping("/employees")
    public Flux<Employee> all() {
      return employeeTemplate.keys("*").flatMap(employeeTemplate.opsForValue()::get);
    }
}
