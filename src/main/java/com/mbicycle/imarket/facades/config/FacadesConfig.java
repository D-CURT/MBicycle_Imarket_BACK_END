package com.mbicycle.imarket.facades.config;

import com.mbicycle.imarket.facades.OrderFacade;
import com.mbicycle.imarket.facades.impl.OrderFacadeImpl;
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
    public ProfileFacade profileFacade() {
        return new SimpleProfileFacade();
    }

    @Bean
    public ProductFacade productFacade() {
        return new ProductFacadeImpl();
    }

    @Bean
    public OrderFacade orderFacade() {
        return new OrderFacadeImpl();
    }

}
