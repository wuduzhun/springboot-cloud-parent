package com.fengzhizi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

// 启动仪表盘监控功能
@EnableHystrixDashboard
@SpringBootApplication
public class DashBoardStartApp {
    public static void main(String[] args) {
        SpringApplication.run(DashBoardStartApp.class,args);
    }
}
