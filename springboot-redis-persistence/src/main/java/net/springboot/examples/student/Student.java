package net.springboot.examples.student;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("Student")
public class Student {
    public static enum Gender {
        MALE, FEMALE
    }

    public Student(String id, String name, Gender gender, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }
    
    private String id;
    private String name;
    private Gender gender;
    private int grade;  
}
