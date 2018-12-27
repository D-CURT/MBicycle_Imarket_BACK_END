package com.mbicycle.imarket.facades.config;

import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.mbicycle.imarket.facades.impl.ProductFacadeImpl;
import com.mbicycle.imarket.facades.impl.SimpleProfileFacade;
import com.mbicycle.imarket.facades.impl.SimpleUserFacade;
import com.mbicycle.imarket.facades.interfaces.ProductFacade;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacadesConfig {

    @Bean
    public UserFacade userFacade() {
        return new SimpleUserFacade();
    }

    @Bean
    public ProductFacade productFacade() {
        return new ProductFacadeImpl();
    }

    @Bean
    public ProfileFacade profileFacade() {
        return new SimpleProfileFacade();
    }
}
