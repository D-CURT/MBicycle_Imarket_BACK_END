package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.dto.GroupDTO;
import com.mbicycle.imarket.facades.interfaces.GroupFacade;
import com.mbicycle.imarket.services.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class GroupFacadeImpl implements GroupFacade {

    @Autowired
    private GroupService groupService;

    @Autowired
    private Converter<Group, GroupDTO> groupConverter;

    @Autowired
    private Converter<GroupDTO, Group> reverseGroupConverter;

    @Override
    public boolean add(GroupDTO groupDTO) {
        return groupService.add(reverseGroupConverter.convert(groupDTO));
    }

    @Override
    public GroupDTO get(String name) {
        return groupConverter.convert(groupService.get(name));
    }

    @Override
    public List<GroupDTO> findByOrderByName() {
        return groupService.findByOrderByName().stream().map(groupConverter::convert).collect(Collectors.toList());
    }

    @Override
    public boolean delete(GroupDTO dto) {
        return groupService.delete(reverseGroupConverter.convert(dto));
    }
}
