package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Role get(RoleType roleType);

    List<Role> findByOrderByRole();

    boolean add(Role role);

    boolean delete(Role role);
}
