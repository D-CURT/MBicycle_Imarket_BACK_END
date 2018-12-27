package com.mbicycle.imarket.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.converters.AbstractConverter;
import com.mbicycle.imarket.dto.CategoryDTO;

public class ReversedCategoryConverter extends AbstractConverter<CategoryDTO, Category> {
    @Override
    public void convert(CategoryDTO source, Category target) {
        target.setId(source.getId());
        target.setName(source.getName());
    }
}
