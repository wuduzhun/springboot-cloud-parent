package com.fengzhizi.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component // 千万要记住这个注解，不要忘记
public class ZuulFilter021 extends ZuulFilter {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /*private static Map<String,String> routingMap = new ConcurrentHashMap<>();
    private static Map<String,String> pathMap = new ConcurrentHashMap<>();
    static {
        routingMap.put("test1","02spring-cloud-provider");
        routingMap.put("test2","02spring-cloud-provider");
        pathMap.put("test1","/provider/test");
        pathMap.put("test2","/provider/test2");
    }*/

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
        // 如果前面的过滤器拦截了请求，那么此处的过滤器就不应该启用
        // 所以我们要先获取前面过滤器的拦截状态，根据状态决定
        RequestContext currentContext = RequestContext.getCurrentContext();
        boolean zuulResponse = currentContext.sendZuulResponse();
        return zuulResponse;
    }

    /**
     * 当过滤器启用的时候我们执行的方法
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.err.println("优先级为 30 的前置过滤器就行了");
        //此过滤器为动态过滤器
        //我们将我们的服务或者地址保存按照某个关系映射保存在某个位置，然后我们每一次都去那里找，比如此处我们先模拟使用 hashmap 来做，生产环境中我们可以使用 redis 来做
        //我们要求，请求接口中必须有一个名字叫 method 的参数，method 就是我们要请求的服务名字
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String method = request.getParameter("method");
        //获取要访问真正的服务
        Map<Object, Object> map = redisTemplate.opsForHash().entries(method);
        String serviceId = (String) map.get("serviceId");
        if(serviceId != null){//代表我们找这个参数对应的服务
            //获取要请求的路径
            String path = (String) map.get("url");
            //通过代码告诉我们的 zuul 我们想要访问哪个服务
            currentContext.put(FilterConstants.SERVICE_ID_KEY,serviceId);
            System.err.println("serviceId -- "+serviceId);
            //String requestURI = currentContext.getRequest().getRequestURI();
            System.err.println("代表我们请求的地址 -- "+path);
            //告诉我们的 zuul 我要请求这个地址
            currentContext.put(FilterConstants.REQUEST_URI_KEY,path);
        }else {
            currentContext.setSendZuulResponse(false);//拦截请求
            currentContext.getResponse().setContentType("text/html;charset=utf-8");
            currentContext.setResponseBody("没有找到对应的服务");
        }
        return null;
    }
}
