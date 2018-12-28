package com.mbicycle.imarket.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.converters.AbstractConverter;
import com.mbicycle.imarket.dto.GroupDTO;
import com.mbicycle.imarket.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReversedGroupCategory extends AbstractConverter<GroupDTO, Group> {

    @Autowired
    @SuppressWarnings("ALL")
    private CategoryService categoryService;

    @Override
    public void convert(GroupDTO source, Group target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setCategory(categoryService.get(source.getCategory()));
    }
}
