package com.mbicycle.imarket.facades;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.dto.RoleDTO;
import com.mbicycle.imarket.converters.RoleConverter;
import org.springframework.stereotype.Component;

@Component
public interface RoleFacade {
/*    @Override
    public Role push(RoleDTO dto, String identifier) {
        Converter<RoleDTO, Role> converter = new RoleConverter();
        return converter.convert(dto);
    }

    @Override
    public RoleDTO pull(Role role) {
        Converter<Role, RoleDTO> converter = new ReverseRoleConverter();
        return converter.convert(role);
    }*/
}
