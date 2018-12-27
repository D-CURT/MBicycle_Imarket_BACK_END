package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    boolean add(Category category);

    Category get(String name);

    List<Category> findByOrderByName();

    boolean delete(Category category);

}
