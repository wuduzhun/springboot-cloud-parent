package com.fengzhizi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// 启用熔断器功能
@EnableCircuitBreaker
@EnableDiscoveryClient // 启动服务发现功能
@SpringBootApplication
public class ProviderStartApp {
    public static void main(String[] args) {
        SpringApplication.run(ProviderStartApp.class,args);
    }
}
