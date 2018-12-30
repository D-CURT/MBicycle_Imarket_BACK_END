package com.mbicycle.imarket.services.securities;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence
            (HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException {
       // response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");     //Preventing standart Authenticate form to pop-up
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 (Unauthorized) - " + authEx.getMessage());
        System.out.println("***SOUT***[authEx]: " + authEx);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("mbicycle");
        super.afterPropertiesSet();
    }
}
