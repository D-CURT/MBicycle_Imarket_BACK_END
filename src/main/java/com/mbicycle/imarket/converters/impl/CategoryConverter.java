package com.mbicycle.imarket.converters.impl;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.converters.AbstractConverter;
import com.mbicycle.imarket.dto.CategoryDTO;

public class CategoryConverter extends AbstractConverter<Category,CategoryDTO> {
    @Override
    public void convert(Category source, CategoryDTO target) {
        target.setId(source.getId());
        target.setName(source.getName());
    }
}
