package com.fengzhizi.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyZuulFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(MyZuulFilter.class);

    @Override
    public String filterType() {

        // 返回当前过滤器类型，决定过滤在什么时候执行
        // pre : 表示在微服务前执行
        String filterType = "pre";
        return filterType;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 判断当前是否要过滤
        // 要过滤：返回true，继续执行run()方法
        // 不要过滤：返回false，直接放行

        // 获取RequestContext对象
        RequestContext requestContext = RequestContext.getCurrentContext();

        // 获取request对象
        HttpServletRequest request = requestContext.getRequest();

        // 判断当前请求是否为signal=hello
        String signal = request.getParameter("signal");

        return "hello".equals(signal);
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("当前请求要进行过滤，run()方法执行了！");
        return null;
    }
}
