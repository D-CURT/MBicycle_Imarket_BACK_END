package com.mbicycle.imarket.services.config;

import com.mbicycle.imarket.services.ProductService;
import com.mbicycle.imarket.services.ProfileService;
import com.mbicycle.imarket.services.RoleService;
import com.mbicycle.imarket.services.UserService;
import com.mbicycle.imarket.services.impl.ProductServiceImpl;
import com.mbicycle.imarket.services.impl.ProfileServiceImpl;
import com.mbicycle.imarket.services.impl.RoleServiceImpl;
import com.mbicycle.imarket.services.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public RoleService roleService() {
        return new RoleServiceImpl();
    }

    @Bean
    public ProfileService profileService() {
        return new ProfileServiceImpl();
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }
}
