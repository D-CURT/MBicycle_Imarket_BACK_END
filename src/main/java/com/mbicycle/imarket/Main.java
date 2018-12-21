package com.mbicycle.imarket;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.utils.generators.ProductGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
      SpringApplication.run(Main.class, args);
      ProductGenerator proGen = new ProductGenerator();
     /* List<Product> allProduct = proGen.generate();*/

    }
}
