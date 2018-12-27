package com.mbicycle.imarket.converters;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.dto.CategoryDTO;

public class CategoryConverter extends AbstractConverter<Category,CategoryDTO> {
    @Override
    public void convert(Category source, CategoryDTO target) {
        target.setName(source.getName());
    }
}
