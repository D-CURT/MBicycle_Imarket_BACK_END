package com.mbicycle.imarket.controllers.filters;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("In filter");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*, authorization");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "authorization");


//        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
