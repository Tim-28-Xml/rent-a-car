package com.tim26.Zuul.zuulserver.filter;

import javax.servlet.http.HttpServletRequest;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import java.util.Enumeration;

@Component
public class ZuulPreFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(ZuulPreFilter.class);


    @Override
    public String filterType() {
        return "pre";
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
        StringBuffer strLog = new StringBuffer();
        strLog.append("\n------ NUEVA PETICION ------\n");
        strLog.append(String.format("Server: %s Metodo: %s Path: %s \n", ctx.getRequest().getServerName(), ctx.getRequest().getMethod(),
                ctx.getRequest().getRequestURI()));
        Enumeration< String > enume = ctx.getRequest().getHeaderNames();
        String header;
        while (enume.hasMoreElements()) {
            header = enume.nextElement();
            strLog.append(String.format("Headers: %s = %s \n", header, ctx.getRequest().getHeader(header)));
        };
        log.info(strLog.toString());
        return null;
    }

}