package com.fengzhizi.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringcloudConfig {

    // 这个注解 @LoadBalanced 让 RestTempplate 有负载均衡功能，通过调用
    // Ribbon 访问 provider 集群
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
