package com.mbicycle.imarket.converters.impl;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.converters.AbstractConverter;
import com.mbicycle.imarket.dto.GroupDTO;

public class GroupConverter extends AbstractConverter<Group,GroupDTO> {
    @Override
    public void convert(Group source, GroupDTO target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setCategory(source.getCategory().getName());
    }
}
