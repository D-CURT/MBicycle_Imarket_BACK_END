package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.dto.RoleDTO;
import com.mbicycle.imarket.facades.interfaces.RoleFacade;

import java.util.List;

public class SimpleRoleFacade implements RoleFacade {
    public boolean add(RoleDTO dto) {
        return false;
    }

    @Override
    public List<RoleDTO> findByOrderByRole() {
        return null;
    }

    @Override
    public boolean delete(RoleDTO dto) {
        return false;
    }
}
