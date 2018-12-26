package com.mbicycle.imarket.utils.converters.reversed;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.dto.RoleDTO;
import com.mbicycle.imarket.utils.converters.Converter;

public class ReverseRoleConverter implements Converter<Role, RoleDTO> {
    @Override
    public RoleDTO convert(Role role) {
        return new RoleDTO(role.getRole().name());
    }
}
