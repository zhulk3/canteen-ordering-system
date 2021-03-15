package com.longkai.canteenorderingsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.longkai.canteenorderingsystem.dao")
public class CanteenOrderingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanteenOrderingSystemApplication.class, args);
    }

}
