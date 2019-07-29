package com.dev2qa.footgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication
@Configuration
@ComponentScan(basePackages = { "com.dev2qa.footgo" })
@EnableAutoConfiguration
public class SpringBootCrudMySqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudMySqlApplication.class, args);
    }
}