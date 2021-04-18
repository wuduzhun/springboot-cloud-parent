package com.fengzhizi.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component // 千万要记住这个注解，不要忘记
public class ZuulFilter03 extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    /**
     * 相同类型过滤器的执行顺序，数字越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 是否启用该过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        boolean zuulResponse = currentContext.sendZuulResponse();
        return zuulResponse;
    }

    /**
     * 过滤器启用时我们执行的方法
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.err.println("优先级为 1 的后置过滤器被执行了！");
        return null;
    }
}
