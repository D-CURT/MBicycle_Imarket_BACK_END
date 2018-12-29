package com.mbicycle.imarket.services.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
               .withUser("admin").password(passwordEncoder().encode("admin"))
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

        http.authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                //.anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/indexLogged").permitAll()
                    .failureUrl("/failureurl")
                .and()
                    .httpBasic()
                .and().csrf().disable();

//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}