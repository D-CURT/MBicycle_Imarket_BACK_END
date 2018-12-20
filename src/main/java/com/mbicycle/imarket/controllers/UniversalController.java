package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.daos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversalController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/index")
    public String getIndex() {
        return "-0-";
    }

    @GetMapping("/search")
    public String getSearch() {
        return "-";
    }    
    

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Optional<Product> getProduct(@RequestParam("id") int id) {
        return productRepository.findById(id);
    }


    @PostMapping(value = "/products/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Product getProductsAdd(@RequestBody Product product) {
        return productRepository.save(product);//тут надо редирект Product page?
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
