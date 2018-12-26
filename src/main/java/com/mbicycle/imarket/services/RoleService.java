package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.daos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public List<Role> findByOrderByRole(){
        return repository.findByOrderByRoleAsc();
    }
}
