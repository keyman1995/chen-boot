package com.chen.boot.chenboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class ChenBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChenBootApplication.class, args);
    }

}
