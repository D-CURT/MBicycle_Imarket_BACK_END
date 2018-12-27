package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> findByOrderByRole();

}
