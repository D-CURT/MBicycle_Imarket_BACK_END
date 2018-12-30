package com.mbicycle.imarket.utils.converters.impl;

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
        List<Group> listGroups = new ArrayList<Group>();
        for(Group group: source.getGroups()) {
            listGroups.add(group);
        }
        target.setGroups(listGroups);
    }
}
