package com.ielop.user_register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableKafka
@EnableAsync
public class UserRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRegisterApplication.class, args);
    }

}
