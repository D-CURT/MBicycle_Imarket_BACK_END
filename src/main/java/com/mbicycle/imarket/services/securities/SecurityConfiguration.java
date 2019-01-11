package com.mbicycle.imarket.services.securities;

import com.mbicycle.imarket.utils.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@Profile({"dev", "demo"})
//@Order(80)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
                .userDetailsService(userDetailsService)
                .csrf().disable()       // Временно пока не добавим токен ф форму
                .authorizeRequests()
                //.antMatchers("/**").permitAll()       //Permit ALL
                .antMatchers("/login").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/orders/**").hasAuthority(RoleType.CUSTOMER.name())
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()   //Already fixed in a better way below
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/logoutdone").addLogoutHandler(new TodosLogoutHandler())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .httpBasic()
                .realmName("mbicycle")
                .authenticationEntryPoint(new MyBasicAuthenticationEntryPoint());
    }


//    /* To allow Pre-flight [OPTIONS] request from browser */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}