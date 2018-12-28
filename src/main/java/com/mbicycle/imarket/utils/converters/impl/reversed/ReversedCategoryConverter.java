package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.CategoryDTO;

public class ReversedCategoryConverter extends AbstractConverter<CategoryDTO, Category> {
    @Override
    public void convert(CategoryDTO source, Category target) {
        target.setId(source.getId());
        target.setName(source.getName());
    }
}
