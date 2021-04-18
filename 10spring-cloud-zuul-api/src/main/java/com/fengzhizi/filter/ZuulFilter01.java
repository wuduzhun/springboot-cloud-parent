package com.fengzhizi.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulFilter01 extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String name = request.getParameter("name");
        String method = request.getParameter("method");
        if(name == null && name.trim().equals("")){
            requestContext.setSendZuulResponse(false);//拦截请求
            requestContext.getResponse().setContentType("text/html;charset=utf-8");
            requestContext.setResponseBody("没有传递 name 参数");
        }
        if(method == null && method.trim().equals("")){
            requestContext.setSendZuulResponse(false);//拦截请求
            requestContext.getResponse().setContentType("text/html;charset=utf-8");
            requestContext.setResponseBody("没有传递 method 参数");
        }
        return null;
    }
}
