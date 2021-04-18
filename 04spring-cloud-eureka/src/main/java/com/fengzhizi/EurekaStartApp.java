package com.fengzhizi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer // 开启 Eureka 服务
@SpringBootApplication
public class EurekaStartApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaStartApp.class,args);
    }
}
