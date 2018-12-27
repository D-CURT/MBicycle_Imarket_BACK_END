package com.mbicycle.imarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
      SpringApplication.run(Main.class, args);
    }
<<<<<<< HEAD

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




=======
>>>>>>> 8c2ba1c68568ba731c759b6de71cc78576b4d631
}
