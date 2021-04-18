package com.fengzhizi.controller;

import com.fengzhizi.pojo.Employee;
import com.fengzhizi.util.ResultEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @RequestMapping("/provider/test")
    public String testParams(String name){
        return "收到了name=" + name;
    }

    @RequestMapping("/provider/test2")
    public Employee getEmp(){
        return new Employee(1,"风之子",24);
    }

    @HystrixCommand(fallbackMethod = "getEmpWithCircuitBreakerup")
    @RequestMapping("/provider/get/emp/with/circuit/breaker")
    public ResultEntity<Employee> getEmpWithCircuitBreaker(@RequestParam("signal") String signal) throws InterruptedException {

        if("qick-bang".equals(signal)){
            throw new RuntimeException();
        }
        if("show-bang".equals(signal)){
            Thread.sleep(5000);
        }

        return ResultEntity.successWithData(new Employee(1,"风之子",24));
    }

    public ResultEntity<Employee> getEmpWithCircuitBreakerup(@RequestParam("signal") String signal){
        String message = "方法执行出现问题，执行断路!";
        return ResultEntity.failed(message + signal);
    }

    @RequestMapping("/provider/get/emp/list/remote")
    public List<Employee> getEmpListRemote(@RequestParam("keyword") String keyword){
        logger.info("keyword = " + keyword);

        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"风之子",23));
        list.add(new Employee(2,"斯蒂芬",24));
        list.add(new Employee(3,"勒布朗",25));
        list.add(new Employee(4,"凤城玫瑰",26));

        return list;
    }

    @RequestMapping("/provider/get/employee/remote")
    public Employee getEmployeeRemote(HttpServletRequest request){

        int serverPort = request.getServerPort();
        return new Employee(1, "风之子 : " + serverPort, 24);
    }
}
