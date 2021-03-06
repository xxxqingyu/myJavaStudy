package com.example.demo;


import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class TokenFilter  extends ZuulFilter {
	//private final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    @Override
    public String filterType() {
        return "pre"; // 可以在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return 0; // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //logger.info("--->>> TokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());

        String token = request.getParameter("access_token");// 获取请求的参数
        
        String tokenHeader = request.getHeader("Authorization");
        
        if(!StringUtils.isEmpty(tokenHeader)){
        	ctx.addZuulRequestHeader("Authorization", tokenHeader);
        }

        if (!StringUtils.isEmpty(token)||!StringUtils.isEmpty(tokenHeader)) {
            ctx.setSendZuulResponse(true); //对请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("access_token is empty");
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
