package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.daos.CategoryRepository;
import com.mbicycle.imarket.dto.CategoryDTO;
import com.mbicycle.imarket.facades.interfaces.CategoryFacade;
import com.mbicycle.imarket.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryFacadeImpl implements CategoryFacade {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private Converter<CategoryDTO,Category> reverseCategoryConverter;

    @Override
    public boolean addCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.findByName(categoryDTO.getName()) == null) {
            Category category = reverseCategoryConverter.convert(categoryDTO);
            categoryService.addCategory(category.getName());
            return true;
        }
        return false;
    }
}
