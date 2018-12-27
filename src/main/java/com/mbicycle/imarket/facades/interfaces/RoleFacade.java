package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.RoleDTO;
import org.springframework.stereotype.Component;

@Component
public interface RoleFacade {
    boolean addRole(RoleDTO dto);
}
