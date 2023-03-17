package com.sunny.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SunnyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunnyBlogApplication.class, args);
    }

}
