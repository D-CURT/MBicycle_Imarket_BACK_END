package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping("/roles/allRolesSortedByRole")
    public List<Role> getAllRolesSortedByRole() {
        return service.findByOrderByRole();
    }


}
