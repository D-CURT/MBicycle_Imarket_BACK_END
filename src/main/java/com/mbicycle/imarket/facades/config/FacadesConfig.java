package com.mbicycle.imarket.facades.config;

<<<<<<< HEAD

import com.mbicycle.imarket.facades.OrderFacade;
import com.mbicycle.imarket.facades.impl.*;
import com.mbicycle.imarket.facades.interfaces.*;
=======
import com.mbicycle.imarket.facades.OrderFacade;
import com.mbicycle.imarket.facades.impl.OrderFacadeImpl;
import com.mbicycle.imarket.facades.impl.ProductFacadeImpl;
import com.mbicycle.imarket.facades.impl.SimpleProfileFacade;
import com.mbicycle.imarket.facades.impl.SimpleUserFacade;
import com.mbicycle.imarket.facades.interfaces.ProductFacade;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
>>>>>>> 8c2ba1c68568ba731c759b6de71cc78576b4d631
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
<<<<<<< HEAD
    }

    @Bean
    public OrderFacade orderFacade(){
        return new OrderFacadeImpl();
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
=======
    }

    @Bean
    public ProductFacade productFacade() {
        return new ProductFacadeImpl();
    }

    @Bean
    public OrderFacade orderFacade() {
        return new OrderFacadeImpl();
    }
>>>>>>> 8c2ba1c68568ba731c759b6de71cc78576b4d631

}
