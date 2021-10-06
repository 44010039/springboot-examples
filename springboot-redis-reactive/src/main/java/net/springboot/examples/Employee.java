package net.springboot.examples;

import lombok.Data;

@Data
public class Employee {
    private String id;
    private String name;
    private String department;
    
    public Employee() {
    }

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    
}
