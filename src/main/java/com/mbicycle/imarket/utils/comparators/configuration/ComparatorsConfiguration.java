package com.mbicycle.imarket.utils.comparators.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComparatorsConfiguration {

    @Bean
    public ComparingManager comparators() throws IllegalAccessException, InstantiationException {
        return new ComparingManager();
    }
}