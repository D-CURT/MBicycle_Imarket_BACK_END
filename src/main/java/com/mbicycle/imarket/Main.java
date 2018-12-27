package com.mbicycle.imarket;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.converters.ProductConverter;
import com.mbicycle.imarket.converters.ReverseProductConverter;
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



}
