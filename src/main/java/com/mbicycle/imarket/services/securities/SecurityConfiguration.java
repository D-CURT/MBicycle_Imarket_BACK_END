package com.mbicycle.imarket.services.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private AlexEntryPoint authenticationEntryPoint;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("log").password(passwordEncoder().encode("pass"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


//        http
//                .userDetailsService(userDetailsService)
//                //.authorizeRequests().antMatchers("/").permitAll().
//                //and()
//                .authorizeRequests().antMatchers("/profiles/**").hasRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/index.html") ///authorization
//                .loginProcessingUrl("/j_spring_security_check")
//                .failureUrl("/products/allProductsSortedByName")                             //убрать после проверки
//                .usernameParameter("j_username").passwordParameter("j_password")
//                .permitAll()
//                .and()
//                .csrf().disable()                                                                  // Временно пока не добавим токен ф форму
//                .authorizeRequests()                                                               //
//                .antMatchers(HttpMethod.POST, "/j_spring_security_check").permitAll(); //
//
        http.authorizeRequests()
                .antMatchers("index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/j_spring_security_check")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}