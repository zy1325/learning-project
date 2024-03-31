package com.zxh.backsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zxh.backsystem.mapper")
public class BacksystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BacksystemApplication.class, args);
    }

}
