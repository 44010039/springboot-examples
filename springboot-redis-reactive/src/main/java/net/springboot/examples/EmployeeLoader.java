package net.springboot.examples;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.ReactiveServerCommands;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class EmployeeLoader implements CommandLineRunner {
    private final ReactiveServerCommands serverCommands;
    private final ReactiveRedisTemplate<String, Employee> employeeTemplate;

    public EmployeeLoader(ReactiveServerCommands serverCommands,
            ReactiveRedisTemplate<String, Employee> employeeTemplate) {
        this.serverCommands = serverCommands;
        this.employeeTemplate = employeeTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        serverCommands.flushAll()
                .thenMany(
                    Flux.just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
                        // 创建对象
                        .map(name -> new Employee(UUID.randomUUID().toString(), name))
                        // 保存到redis
                        .flatMap(coffee -> employeeTemplate.opsForValue().set(coffee.getId(), coffee))
                )
                .thenMany(
                    // 显示所有key值
                    employeeTemplate.keys("*").flatMap(employeeTemplate.opsForValue()::get)
                )
                .subscribe(
                    b -> log.info("Boolean: {}", b), 
                    e -> log.error("Exception {}", e.getMessage()));
        
    }
}
