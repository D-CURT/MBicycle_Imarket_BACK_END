package com.mbicycle.imarket.utils.converters.impl;

import com.mbicycle.imarket.beans.dto.CategoryGroupDTO;
import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

public class CategoryConverter extends AbstractConverter<Category,CategoryDTO> {
    @Override
    public void convert(Category source, CategoryDTO target) {
        target.setId(source.getId());
        target.setName(source.getName());
        List<CategoryGroupDTO> strListGroups = new ArrayList<CategoryGroupDTO>();
        for(Group group: source.getGroups()) {
            CategoryGroupDTO categoryGroupDTO = new CategoryGroupDTO(group.getName());
            strListGroups.add(categoryGroupDTO);
        }
        target.setGroups(strListGroups);
    }
}
