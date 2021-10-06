package net.springboot.examples;

import java.util.Map;

import lombok.Data;

@Data
public class Sms {
    private String phone;
    private String message;
    private Map<String, String> params;

    public Sms() {
    }

    public Sms(String phone, String message) {
        this.phone = phone;
        this.message = message;
    }

    
}
