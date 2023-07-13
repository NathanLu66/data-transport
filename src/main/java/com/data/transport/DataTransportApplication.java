package com.data.transport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.data.transport.mapper")
public class DataTransportApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataTransportApplication.class, args);
    }
}