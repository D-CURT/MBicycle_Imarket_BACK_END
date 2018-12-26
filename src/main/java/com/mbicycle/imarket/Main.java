package com.mbicycle.imarket;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.utils.generators.BaseGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.List;

@SpringBootApplication

@ImportResource({"classpath*:appconfig-security.xml"})
public class Main {

    public static void main(String[] args) {

       SpringApplication.run(Main.class, args);

    }
}
