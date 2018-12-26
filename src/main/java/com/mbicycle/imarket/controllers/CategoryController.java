package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/categories/allCategoriesSortedByName")
    public List<Category> getAllCategoriesSortedByName() {
        return service.findByOrderByName();
    }
}
