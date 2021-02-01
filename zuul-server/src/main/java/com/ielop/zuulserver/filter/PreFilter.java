package com.ielop.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class PreFilter extends ZuulFilter {

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
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getHeader("Authorization") != null){
            ctx.addZuulRequestHeader("Authorization", request.getHeader("Authorization"));
        }

        System.out.println(
                "Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());

        return null;
    }

}
