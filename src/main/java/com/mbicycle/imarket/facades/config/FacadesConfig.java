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

    public UserFacade userFacade() {
        return new SimpleUserFacade();
    }

    @Bean
    public ProfileFacade profileFacade() {
        return new SimpleProfileFacade();
    }

//    @Bean
//    public CategoryFacade categoryFacade(){
//        return new CategoryFacadeImpl();
//    }
//
//    @Bean
//    public GroupFacade groupFacade(){
//        return new GroupFacadeImpl();
//    }
//
//    @Bean
//    public CouponFacade couponFacade(){
//        return new CouponFacadeImpl();
//    }

}
