package com.mbicycle.imarket.utils.converters.config;

import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.utils.converters.Converter;
import com.mbicycle.imarket.utils.converters.impl.*;
import com.mbicycle.imarket.utils.converters.impl.reversed.*;
import com.mbicycle.imarket.beans.dto.*;
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

    @Bean
    public Converter<Category, CategoryDTO> categoryConverter() {
        return new CategoryConverter();
    }

    @Bean
    public Converter<CategoryDTO, Category> reverseCategoryConverter() {
        return new ReversedCategoryConverter();
    }

    @Bean
    public Converter<Group, GroupDTO> groupConverter() {
        return new GroupConverter();
    }

    @Bean
    public Converter<GroupDTO, Group> reverseGroupConverter() {
        return new ReversedGroupCategory();
    }

    @Bean
    public Converter<Coupon, CouponDTO> couponConverter() {
        return new CouponConverter();
    }

    @Bean
    public Converter<CouponDTO, Coupon> reverseCouponConverter() {
        return new ReversedCouponConverter();
    }

    @Bean
    public Converter<Order, OrderDTO> orderConverter(){
        return new OrderConverter();
    }

    @Bean
    public Converter<OrderDTO, Order> reverseOrderConverter(){
        return new ReversedOrderConverter();
    }
}
