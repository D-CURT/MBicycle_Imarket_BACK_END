package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.converters.GroupConverter;
import com.mbicycle.imarket.daos.GroupRepository;
import com.mbicycle.imarket.dto.GroupDTO;
import com.mbicycle.imarket.facades.interfaces.GroupFacade;
import com.mbicycle.imarket.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupFacadeImpl implements GroupFacade {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private Converter<GroupDTO,Group> reverseGroupConverter;

    @Override
    public boolean addGroup(GroupDTO groupDTO) {
        if (groupRepository.findByName(groupDTO.getName())==null){
            Group group = reverseGroupConverter.convert(groupDTO);
            groupService.addGroup(group.getName(),group.getCategory().getName());
            return true;
        }
        return false;
    }
}
