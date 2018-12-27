package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories/allCategoriesSortedByName")
    public List<Category> getAllCategoriesSortedByName() {
        return categoryService.findByOrderByName();
    }

    @PostMapping(value = "/categories/add/{name}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addCategory(@PathVariable String name) {
        categoryService.addCategory(name);
    }
}
