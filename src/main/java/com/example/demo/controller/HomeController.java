package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Simple, database-free endpoints used to smoke-test a CodeBox Cloud deployment.
 * (The existing TutorialController under /api/tutorials needs MongoDB; these do not.)
 */
@RestController
@CrossOrigin(origins = "*")
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("app", "demotest");
        body.put("message", "Hello from demotest - deployed by CodeBox Cloud!");
        body.put("time", Instant.now().toString());
        return body;
    }

    @GetMapping("/hello")
    public Map<String, String> hello(@RequestParam(defaultValue = "World") String name) {
        return Map.of("message", "Hello, " + name + "!");
    }

    @GetMapping("/api/ping")
    public Map<String, String> ping() {
        return Map.of("status", "ok");
    }
}
