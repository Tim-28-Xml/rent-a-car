package com.tim26.ChatService.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private TokenUtils tokenUtils;

    public TokenAuthenticationFilter(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String username;
        String authToken = tokenUtils.getToken(httpRequest);

        if(authToken != null){
            String permString = tokenUtils.getPermissionsFromToken(authToken);
            username = tokenUtils.getUsernameFromToken(authToken);

            if (permString != null) {
                Set<SimpleGrantedAuthority> authorities = new HashSet<>();

                String[] tokens = permString.split(",");
                for (String token : tokens) {
                    authorities.add(new SimpleGrantedAuthority(token));
                }

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}
