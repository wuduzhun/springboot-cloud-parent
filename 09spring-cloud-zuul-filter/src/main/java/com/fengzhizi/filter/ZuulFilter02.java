package com.fengzhizi.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component // 千万要记住这个注解，不要忘记
public class ZuulFilter02 extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 相同类型过滤器的执行顺序，数字越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 30;
    }

    /**
     * 是否启用该过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器启用时我们执行的方法
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.err.println("优先级为 30 的前置过滤器被执行了！");
        return null;
    }
}
