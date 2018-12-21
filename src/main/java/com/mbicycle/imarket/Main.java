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
      SpringApplication.run(Main.class, args);


      //Example BaseGenerator API
      BaseGenerator generator = new BaseGenerator();
      List<Product> allProduct = generator.generateProduсts(1000); //parameter - count. It creates 2 categories with 2 groups, total = 2 * 2 * count
      List<User> allUsers = generator.generateUsers(1000); // parameter - count. Simultaneously, profiles are generated
      List<Order> allOrders = generator.generateOrders(15); // parameter - percent!!! Always it have be past generateUsers, because they transmit profiles

    }
}
