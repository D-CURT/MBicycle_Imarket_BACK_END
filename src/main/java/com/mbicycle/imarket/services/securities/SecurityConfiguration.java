package com.mbicycle.imarket.services.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().
                and().authorizeRequests().antMatchers("/profiles/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/") ///authorization
                .loginProcessingUrl("/security_check")
                .usernameParameter("j_username").passwordParameter("j_password")
                .permitAll();

        httpSecurity.userDetailsService(userDetailsService);


    }

}