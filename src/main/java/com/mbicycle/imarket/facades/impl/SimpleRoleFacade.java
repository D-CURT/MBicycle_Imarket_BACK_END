package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.dto.RoleDTO;
import com.mbicycle.imarket.converters.RoleConverter;

public class SimpleRoleFacade {

    public Role addRole(RoleDTO dto) {
        Converter<RoleDTO, Role> converter = new RoleConverter();
        return converter.convert(dto);
    }

}
