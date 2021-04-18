package com.fengzhizi.controller;

import com.fengzhizi.pojo.Employee;
import com.fengzhizi.service.api.EmployeeRemoteService;
import com.fengzhizi.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignConsumerController {

    // 调用远程微服务接口，后面调用本地方法一样直接使用
    @Autowired
    private EmployeeRemoteService employeeRemoteService;

    @RequestMapping("/feign/consumer/test/fallback")
    public ResultEntity<Employee> testFallBack(@RequestParam("signal") String signal){
        return employeeRemoteService.getEmpWithCircuitBreaker(signal);
    }

    @RequestMapping("/feign/consumer/get/emp")
    public Employee getEmployeeRemote(){
        Employee employee = employeeRemoteService.getEmployeeRemote();
        employee.setEmpName(employee.getEmpName()+"---来自feign");
        return employee;
    }

    @RequestMapping("/feign/consumer/search")
    public List<Employee> getEmpListRemote(String keyword){
        return employeeRemoteService.getEmpListRemote(keyword);
    }
}

