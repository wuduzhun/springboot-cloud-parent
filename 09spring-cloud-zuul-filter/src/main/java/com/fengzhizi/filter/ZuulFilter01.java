package com.fengzhizi.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component // 千万要记住这个注解，不要忘记
public class ZuulFilter01 extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre"; //
    }

    /**
     * 相同类型过滤器的执行顺序，数字越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 10;
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
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String signal = request.getParameter("name");
        if(signal==null && signal.trim().equals("")){
            // 拦截请求
            requestContext.setSendZuulResponse(false);
            requestContext.getResponse().setContentType("text/html;charset=utf-8");
            requestContext.setResponseBody("没有传递 signal 参数");
        }
        return null;
    }
}
