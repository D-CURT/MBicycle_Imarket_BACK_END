package com.mbicycle.imarket.services.config;

import com.mbicycle.imarket.services.impl.*;
import com.mbicycle.imarket.services.interfaces.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {
    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl();
    }

    @Bean
    public CouponService couponService() {
        return new CouponServiceImpl();
    }

    @Bean
    public GroupService groupService() {
        return new GroupServiceImpl();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }

    @Bean
    public ProfileService profileService() {
        return new ProfileServiceImpl();
    }

    @Bean
    public RoleService roleService() {
        return new RoleServiceImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}
