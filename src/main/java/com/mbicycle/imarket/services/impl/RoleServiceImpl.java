package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.daos.RoleRepository;
import com.mbicycle.imarket.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    public List<Role> findByOrderByRole(){
        return repository.findByOrderByRoleAsc();
    }
}
