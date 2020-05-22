package com.mokhovav.inspiration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"com.mokhovav.base_spring_boot_project", "com.mokhovav.inspiration"})
public class Inspiration {
    public static void main(String[] args) {
        SpringApplication.run(Inspiration.class, args);
    }
}

