package net.springboot.examples.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String home() {
        return "Hello Spring Boot 2.0!";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}