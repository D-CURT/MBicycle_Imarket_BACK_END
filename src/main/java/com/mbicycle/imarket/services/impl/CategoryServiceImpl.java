package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.daos.CategoryRepository;
import com.mbicycle.imarket.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    public boolean addCategory(String name) {
        if (repository.findByName(name) == null) {
            addCategory(new Category(name));
        }
        return true;
    }

    public void addCategory(Category category) {
        repository.save(category);
    }

    public Category getCategory(String name){
        return repository.findByName(name);
    }

    public List<Category> findByOrderByName(){
        return repository.findByOrderByNameAsc();
    }
}
