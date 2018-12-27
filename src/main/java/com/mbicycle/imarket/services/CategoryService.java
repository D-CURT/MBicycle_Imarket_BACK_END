package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    boolean addCategory(String name);

    void addCategory(Category category);

    Category getCategory(String name);

    List<Category> findByOrderByName();

}
