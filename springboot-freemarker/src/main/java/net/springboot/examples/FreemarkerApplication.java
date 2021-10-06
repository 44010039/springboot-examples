package net.springboot.examples;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class FreemarkerApplication implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    public FreemarkerApplication(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FreemarkerApplication.class, args);
    }

    @Override
    public void run(String...args) throws Exception {

        employeeRepository.save(new Employee("Ramesh", "Fadatare", "ramesh@gmail.com"));
        employeeRepository.save(new Employee("Tom", "Cruise", "tom@gmail.com"));
        employeeRepository.save(new Employee("John", "Cena", "john@gmail.com"));
        employeeRepository.save(new Employee("tony", "stark", "stark@gmail.com"));
        // get list of employees
        List < Employee > employees = employeeRepository.findAll();
        employees.forEach(employee -> log.info(employee.toString()));
    }  
}
