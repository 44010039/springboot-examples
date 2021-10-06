package net.springboot.examples;

import java.util.Map;

import lombok.Data;

@Data
public class Email {
    private String email;
    private String subject;
    private String content;
    private Map<String, String> params;
    
    public Email() {
    }

    public Email(String email, String subject, String content) {
        this.email = email;
        this.subject = subject;
        this.content = content;
    }

    
}
