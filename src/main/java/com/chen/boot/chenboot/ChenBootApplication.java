package com.chen.boot.chenboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
@MapperScan("com.chen.boot.mapper")
public class ChenBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChenBootApplication.class, args);
    }

}
