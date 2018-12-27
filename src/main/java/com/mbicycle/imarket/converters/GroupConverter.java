package com.mbicycle.imarket.converters;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.dto.GroupDTO;

public class GroupConverter extends AbstractConverter<Group,GroupDTO> {
    @Override
    public void convert(Group source, GroupDTO target) {
        target.setName(source.getName());
        target.setCategory(source.getCategory().getName());
    }
}
