package com.mbicycle.imarket.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class UniversalController {

    @GetMapping("/index")
    public String getIndex() {
        return "-0-";
    }

    @GetMapping("/search")
    public String getSearch() {
        return "-";
    }    
    
    @GetMapping("/products")
    public String getProducts() {
        return "-0-";
    }    
    
    @GetMapping("/products/add")
    public String getProductsAdd() {
        return "-0-";
    }    
    
    @GetMapping("/products/edit")
    public String getProductsEdit() {
        return "-0-";
    }    
    
    @GetMapping("/products/delete")
    public String getProductsDelete() {
        return "-0-";
    }    
    
    @GetMapping("/cart")
    public String getCart() {
        return "-0-";
    }

    @GetMapping("/orders")
    public String getOrders() {
        return "-0-";
    }

}
