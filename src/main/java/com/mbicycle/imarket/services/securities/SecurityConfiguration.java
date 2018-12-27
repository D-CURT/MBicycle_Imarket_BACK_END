package com.mbicycle.imarket.services.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity
                .userDetailsService(userDetailsService)
                //.authorizeRequests().antMatchers("/").permitAll().
                //and()
                .authorizeRequests().antMatchers("/profiles/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/") ///authorization
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/products/allProductsSortedByName")                             //убрать после проверки
                .usernameParameter("j_username").passwordParameter("j_password")
                .permitAll()
                .and()
                .csrf().disable()                                                                  // Временно пока не добавим токен ф форму
                .authorizeRequests()                                                               //
                .antMatchers(HttpMethod.POST, "/j_spring_security_check").permitAll(); //


    }

}