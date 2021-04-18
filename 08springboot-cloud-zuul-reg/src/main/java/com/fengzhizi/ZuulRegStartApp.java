package com.fengzhizi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
public class ZuulRegStartApp {
    public static void main(String[] args) {
        SpringApplication.run(ZuulRegStartApp.class,args);
    }

    /**
     * 这是一个表达式模式的服务代理，比如我们传递的名字叫users-v1,会自动代理成 v1/users
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper(){
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }
}
