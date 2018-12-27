package com.mbicycle.imarket;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.converters.*;
import com.mbicycle.imarket.dto.OrderDTO;
import com.mbicycle.imarket.dto.ProductDTO;
import com.mbicycle.imarket.services.ProductService;
import com.mbicycle.imarket.services.impl.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
      SpringApplication.run(Main.class, args);
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
    public Converter<Order, OrderDTO> orderConverter() { return new OrderConverter(); }

    @Bean
    public Converter<OrderDTO, Order> reverseOrderConverter() { return new ReverseOrderConverter(); }




}
