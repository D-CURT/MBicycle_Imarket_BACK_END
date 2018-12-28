package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.daos.CategoryRepository;
import com.mbicycle.imarket.dto.CategoryDTO;
import com.mbicycle.imarket.facades.interfaces.CategoryFacade;
import com.mbicycle.imarket.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class CategoryFacadeImpl implements CategoryFacade {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Converter<Category, CategoryDTO> categoryConverter;

    @Autowired
    private Converter<CategoryDTO, Category> reverseCategoryConverter;

    @Override
    public boolean add(CategoryDTO categoryDTO) {
        return categoryService.add(reverseCategoryConverter.convert(categoryDTO));
    }

    @Override
    public List<CategoryDTO> findByOrderByName() {
        return categoryService.findByOrderByName().stream().map(categoryConverter::convert).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO get(String name) {
        return categoryConverter.convert(categoryService.get(name));
    }

    @Override
    public boolean delete(CategoryDTO dto) {
        return categoryService.delete(reverseCategoryConverter.convert(dto));
    }
}
