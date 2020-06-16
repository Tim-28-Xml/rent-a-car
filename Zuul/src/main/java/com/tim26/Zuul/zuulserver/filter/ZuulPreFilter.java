package com.tim26.Zuul.zuulserver.filter;

import javax.servlet.http.HttpServletRequest;

import com.tim26.Zuul.zuulserver.client.AuthClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ZuulPreFilter extends ZuulFilter {


    @Autowired
    private AuthClient authClient;

    @Override
    public String filterType() {
        return "pre ";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private void setFailedRequest(String body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();


        String authHeader = request.getHeader("Authorization");
        String token = "";

        if (authHeader != null && authHeader.contains("Bearer "))
            token = authHeader.replace("Bearer ", "");


        try {
            boolean verified;

            if(!token.equals("")){
                verified = authClient.verify(token);
                if(!verified)
                    setFailedRequest("User not verified", 403);
            }

            ctx.addZuulRequestHeader("Authorization", authHeader);

        } catch (FeignException.NotFound e) {
            setFailedRequest("Consumer does not exist!", 403);
        }

        return null;
    }

}