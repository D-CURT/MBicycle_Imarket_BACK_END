package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.daos.CategoryRepository;
import com.mbicycle.imarket.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    @SuppressWarnings("ALL")
    private CategoryRepository repository;

    @Override
    public boolean add(Category category) {
        String name = category.getName();
        if (get(name) == null) {
            repository.save(category);
        }
        return get(name) != null;
    }

    @Override
    public Category get(String name){
        return repository.findByName(name);
    }

    @Override
    public List<Category> findByOrderByName(){
        return repository.findByOrderByNameAsc();
    }

    @Override
    public boolean delete(Category category) {
        String name = category.getName();
        if (get(name) != null) {
            repository.delete(category);
        }
        return get(name) == null;
    }
}
