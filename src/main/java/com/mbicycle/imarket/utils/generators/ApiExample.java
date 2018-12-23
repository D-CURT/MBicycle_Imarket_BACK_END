package com.mbicycle.imarket.utils.generators;

import com.mbicycle.imarket.beans.entities.*;

import java.util.List;

public class ApiExample {
    public static void main(String ars[]){

        //Example BaseGenerator API
        BaseGenerator generator = new BaseGenerator();
        List<Product> allProduct = generator.generateProduсts(1000); //parameter - count. It creates 2 categories with 2 groups, total = 2 * 2 * count = 2*2*1000 = 4000 products
        List<Category> categories = generator.getCategories(); // get these 2 categories, it have to past generateProduсts()
        List<Group> groups = generator.getGroups(); // get these 2 * 2 = 4 categories, it have to past generateProduсts()
        List<User> allUsers = generator.generateUsers(1000); // parameter - count. Simultaneously, profiles are generated
        List<Order> allOrders = generator.generateOrders(15); // parameter - percent!!! Always it have to be past generateUsers(), because they transmit profiles

    }
}
