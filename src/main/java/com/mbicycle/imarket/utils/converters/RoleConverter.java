package com.mbicycle.imarket.utils.converters;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.dto.RoleDTO;
import com.mbicycle.imarket.utils.enums.RoleType;

public class RoleConverter implements Converter<RoleDTO, Role> {
    @Override
    public Role convert(RoleDTO roleDTO) {
        return new Role(RoleType.valueOf(roleDTO.getRole()));
    }
}
