package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.daos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public void addCategory(Category category) {
        repository.save(category);
    }

    public Category getCategory(String name){
        return repository.findByName(name);
    }

    public List<Category> list(){
        return repository.findByOrderByNameAsc();
    }
}
