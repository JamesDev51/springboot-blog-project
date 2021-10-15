package com.jamesdev.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringbootBlogProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogProjectApplication.class, args);
    }

}
