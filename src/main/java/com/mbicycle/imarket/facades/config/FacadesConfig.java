package com.mbicycle.imarket.facades.config;

<<<<<<< HEAD
import com.mbicycle.imarket.facades.ProductFacade;
import com.mbicycle.imarket.facades.impl.ProductFacadeImpl;
=======
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.mbicycle.imarket.facades.impl.ProductFacadeImpl;
import com.mbicycle.imarket.facades.impl.SimpleProfileFacade;
import com.mbicycle.imarket.facades.impl.SimpleUserFacade;
import com.mbicycle.imarket.facades.interfaces.ProductFacade;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
>>>>>>> bef5eb2a18dbf444800576f39546c847c55d41da
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacadesConfig {

    @Bean
<<<<<<< HEAD
    public ProductFacade productFacade(){
        return new ProductFacadeImpl();
    }
=======
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
>>>>>>> bef5eb2a18dbf444800576f39546c847c55d41da
}
