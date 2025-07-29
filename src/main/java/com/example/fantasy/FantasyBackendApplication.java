package com.example.fantasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FantasyBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FantasyBackendApplication.class, args);
    }
}
