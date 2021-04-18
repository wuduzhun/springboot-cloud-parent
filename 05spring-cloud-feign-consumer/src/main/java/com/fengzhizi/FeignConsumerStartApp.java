package com.fengzhizi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // 启用feign客服端功能
@EnableDiscoveryClient
@SpringBootApplication
public class FeignConsumerStartApp {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerStartApp.class,args);
    }
}
