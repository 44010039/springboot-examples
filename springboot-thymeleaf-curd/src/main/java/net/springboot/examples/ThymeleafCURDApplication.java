package net.springboot.examples;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ThymeleafCURDApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    public ThymeleafCURDApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafCURDApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new User("Ramesh", "ramesh@gmail.com"));
        userRepository.save(new User("Tom", "tom@gmail.com"));
        userRepository.save(new User("John", "john@gmail.com"));
        userRepository.save(new User("tony", "stark@gmail.com"));
        // get list of employees
        List<User> employees = userRepository.findAll();
        employees.forEach(employee -> log.info(employee.toString()));
    }
}
