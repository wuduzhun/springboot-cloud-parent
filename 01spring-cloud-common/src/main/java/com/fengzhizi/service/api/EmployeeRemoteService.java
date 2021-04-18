package com.fengzhizi.service.api;

import com.fengzhizi.pojo.Employee;
import com.fengzhizi.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "02spring-cloud-provider")
public interface EmployeeRemoteService {

    // 远程调用接口方法
    // 要求@RequestMapping注解映射的地址一致
    // 要求方法的声明一致
    @RequestMapping("/provider/get/employee/remote")
    public Employee getEmployeeRemote();

    @RequestMapping("/provider/get/emp/list/remote")
    public List<Employee> getEmpListRemote(@RequestParam("keyword") String keyword);

    @RequestMapping("/provider/get/emp/with/circuit/breaker")
    public ResultEntity<Employee> getEmpWithCircuitBreaker(@RequestParam("signal") String signal);
}
