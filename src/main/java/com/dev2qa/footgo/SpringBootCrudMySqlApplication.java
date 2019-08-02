package com.dev2qa.footgo;

import com.dev2qa.footgo.util.TeamGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.dev2qa.footgo" })

public class SpringBootCrudMySqlApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootCrudMySqlApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudMySqlApplication.class, args);
    }
}