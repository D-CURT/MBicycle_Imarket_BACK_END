package com.mbicycle.imarket;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.utils.generators.BaseGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication/*(scanBasePackages = "com.mbicycle.imarket")*/
public class Main {
    public static void main(String[] args) {
     // SpringApplication.run(Main.class, args);

      BaseGenerator generator = new BaseGenerator();
      List<Product> allProduct = generator.generateProduсts();
      List<User> allUsers = generator.generateUsers();
      List<Order> allOrders = generator.generateOrders();

    }
}
