package com.tim26.AuthenticationService.security.auth;

import com.tim26.AuthenticationService.controller.AuthenticationController;
import com.tim26.AuthenticationService.security.TokenUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER=LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private TokenUtils tokenUtils;

    private UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
        this.tokenUtils = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String username;
        String authToken = tokenUtils.getToken(httpServletRequest);

        for (String header : Collections.list(httpServletRequest.getHeaderNames())){
            LOGGER.info("This is header: " + header);
        }

        LOGGER.info("This is request: " + httpServletRequest.getHeader("Authorization"));
        LOGGER.info("This is token: " + authToken);

        if(authToken != null){
            username = tokenUtils.getUsernameFromToken(authToken);
            LOGGER.info("This is username: " + username);

            if(username != null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (tokenUtils.validateToken(authToken, userDetails)) {
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
