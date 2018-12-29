package com.mbicycle.imarket.facades.config;

import com.mbicycle.imarket.facades.impl.*;
import com.mbicycle.imarket.facades.interfaces.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacadesConfig {

    @Bean
    public ProductFacade productFacade() {
        return new ProductFacadeImpl();
    }

    @Bean
    public UserFacade userFacade() {
        return new SimpleUserFacade();
    }

    @Bean
    public RoleFacade roleFacade() {
        return new SimpleRoleFacade();
    }

    @Bean
    public ProfileFacade profileFacade() {
        return new SimpleProfileFacade();
    }

    @Bean
    public OrderFacade orderFacade() {
        return new OrderFacadeImpl();
    }

    @Bean
    public CategoryFacade categoryFacade() {
        return new CategoryFacadeImpl();
    }

    @Bean
    public GroupFacade groupFacade() {
        return new GroupFacadeImpl();
    }

    @Bean
    public CouponFacade couponFacade() {
        return new CouponFacadeImpl();
    }
<<<<<<< HEAD


=======
>>>>>>> b52a278533d1b9381ddb2b544051893ecd80d0a1
}
