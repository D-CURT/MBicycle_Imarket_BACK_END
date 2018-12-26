package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> findByOrderByRole();
}
