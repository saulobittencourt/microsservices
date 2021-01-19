package com.ielop.user_register_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableKafka
@EnableAsync
public class UserRegisterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRegisterServiceApplication.class, args);
    }

}
