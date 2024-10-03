package com.test.coursespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CoursespringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursespringApplication.class, args);
    }

}
