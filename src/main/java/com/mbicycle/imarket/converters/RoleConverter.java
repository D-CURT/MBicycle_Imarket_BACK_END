package com.mbicycle.imarket.converters;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.converters.AbstractConverter;
import com.mbicycle.imarket.dto.RoleDTO;
import com.mbicycle.imarket.utils.enums.RoleType;

public class RoleConverter extends AbstractConverter<RoleDTO, Role> {
    @Override
    public void convert(RoleDTO roleDTO, Role role) {
        role.setRole(RoleType.valueOf(roleDTO.getRole()));
    }
}
