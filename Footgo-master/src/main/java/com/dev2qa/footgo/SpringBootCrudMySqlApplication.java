package com.dev2qa.footgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.dev2qa.footgo")
@EnableAutoConfiguration
//@ComponentScan(basePackages = { "com.dev2qa.footgo" })

public class SpringBootCrudMySqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudMySqlApplication.class, args);
    }
}