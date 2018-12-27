package com.mbicycle.imarket.services.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.hibernate.criterion.Restrictions.and;


@Configuration
@EnableWebSecurity
//@Profile({"dev", "demo"})
//@Order(80)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MyBasicAuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity
                .userDetailsService(userDetailsService)
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()

                .and()
                .formLogin()
                .loginPage("/index") ///authorization
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/products/allProductsSortedByName")                             //убрать после проверки
                .usernameParameter("j_username").passwordParameter("j_password")
                .permitAll()


                .and().

                 csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**").hasRole("ADMIN")
                .and().httpBasic().realmName("mbicycle").authenticationEntryPoint(new MyBasicAuthenticationEntryPoint());
                //.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        //  .and()
        //  .csrf().disable()                                                                  // Временно пока не добавим токен ф форму
        //   .authorizeRequests()                                                               //
        //  .antMatchers(HttpMethod.POST, "/j_spring_security_check")
        //  .permitAll()


        //  .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
//               .and().logout().clearAuthentication(true).invalidateHttpSession(true)
//               .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
//                .and()
//                .csrf().disable();

//        httpSecurity.addFilterAfter(new CustomFilter(),
//                BasicAuthenticationFilter.class);

    }

    /* To allow Pre-flight [OPTIONS] request from browser */
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

}