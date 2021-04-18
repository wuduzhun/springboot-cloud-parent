package com.fengzhizi.factory;

import com.fengzhizi.pojo.Employee;
import com.fengzhizi.service.api.EmployeeRemoteService;
import com.fengzhizi.util.ResultEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 1.实现Consumer端服务降级功能
 * 2.实现FallBcakFactory接口时要传入@FeignClient注解标记的接口类型
 3.在create()方法中返回@FeignClient注解标记接口类型对象，当Provider调用失败后，会执行这个对象的对应方法
 4.这个类必须使用@Component注解将当前类的对象加入IOC容器，当前类必须能够被扫描到
 */
@Component
class MyFallBackFactory implements FallbackFactory<EmployeeRemoteService> {
    @Override
    public EmployeeRemoteService create(Throwable throwable) {


        return new EmployeeRemoteService() {
            @Override
            public Employee getEmployeeRemote() {
                return null;
            }

            @Override
            public List<Employee> getEmpListRemote(String keyword) {
                return null;
            }

            @Override
            public ResultEntity<Employee> getEmpWithCircuitBreaker(String signal) {
                return ResultEntity.failed("降级机制生效 ：" + throwable.getMessage());
            }
        };
    }
}