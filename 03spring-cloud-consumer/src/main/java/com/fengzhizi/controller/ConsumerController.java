package com.fengzhizi.controller;

import com.fengzhizi.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/get/employee/remote")
    public Employee getEmployeeRemote(){

        // 1.声明远程微服务的主机地址加端口
        // String host = "http://localhost:1000";

        // 将远程服务调用地址从“ip + 端口号”改成“微服务名称”
        String host = "http://02SPRING-CLOUD-PROVIDER";

        // 2.声明具体调用功能的 URL 地址
        String url = "/provider/get/employee/remote";

        // 3.通过 RestTemplate 调用远程服务
        Employee employee = restTemplate.getForObject(host + url, Employee.class);

        employee.setEmpName(employee.getEmpName() + "-- 来自消费者");

        return employee;
    }
}
