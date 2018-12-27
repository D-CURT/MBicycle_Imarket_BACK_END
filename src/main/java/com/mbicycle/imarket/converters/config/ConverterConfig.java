package com.mbicycle.imarket.converters.config;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.converters.*;
import com.mbicycle.imarket.converters.impl.ProductConverter;
import com.mbicycle.imarket.converters.impl.ProfileConverter;
import com.mbicycle.imarket.converters.impl.UserConverter;
import com.mbicycle.imarket.converters.impl.reversed.ReverseProductConverter;
import com.mbicycle.imarket.converters.impl.reversed.ReversedProfileConverter;
import com.mbicycle.imarket.converters.impl.reversed.ReversedUserConverter;
import com.mbicycle.imarket.dto.ProductDTO;
import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {

    @Bean
    public Converter<User, UserDTO> userConverter() {
        return new UserConverter();
    }

    @Bean
    public Converter<UserDTO, User> reversedUserConverter() {
        return new ReversedUserConverter();
    }

    @Bean
    public Converter<Profile, ProfileDTO> profileConverter() {
        return new ProfileConverter();
    }

    @Bean
    public Converter<ProfileDTO, Profile> reversedProfileConverter() {
        return new ReversedProfileConverter();
    }

    @Bean
    public Converter<Product, ProductDTO> productConverter() {
        return new ProductConverter();
    }

    @Bean
    public Converter<ProductDTO, Product> reverseProductConverter() {
        return new ReverseProductConverter();
    }
}
