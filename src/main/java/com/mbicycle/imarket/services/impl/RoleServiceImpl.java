package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.daos.RoleRepository;
import com.mbicycle.imarket.services.interfaces.RoleService;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    @Autowired
    @SuppressWarnings("ALL")
    private RoleRepository repository;

    public Role get(RoleType roleType) {
        return repository.findByRole(roleType);
    }

    public List<Role> findByOrderByRole(){
        return repository.findByOrderByRoleAsc();
    }

    @Override
    public boolean add(Role role) {
        RoleType roleType = role.getRole();
        if (get(roleType) == null) {
            repository.save(role);
        }
        return get(roleType) != null;
    }

    @Override
    public boolean delete(Role role) {
        RoleType roleType = role.getRole();
        if (get(roleType) != null) {
            repository.delete(role);
        }
        return get(roleType) == null;
    }
}
