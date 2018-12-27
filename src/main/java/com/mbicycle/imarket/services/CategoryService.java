package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Category;

import java.util.List;

public interface CategoryService {

    boolean addCategory(String name);

    void addCategory(Category category);

    Category getCategory(String name);

    List<Category> findByOrderByName();

}
