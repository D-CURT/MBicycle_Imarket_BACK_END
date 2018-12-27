package com.mbicycle.imarket.facades.config;

import com.mbicycle.imarket.facades.ProductFacade;
import com.mbicycle.imarket.facades.impl.ProductFacadeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacadesConfig {

    @Bean
    public ProductFacade productFacade(){
        return new ProductFacadeImpl();
    }
}
