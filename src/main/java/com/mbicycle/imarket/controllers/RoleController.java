package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.RoleDTO;
import com.mbicycle.imarket.facades.interfaces.RoleFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    @SuppressWarnings("ALL")
    private RoleFacade facade;

    @GetMapping("/roles/allRolesSortedByRole")
    public List<RoleDTO> getAllRolesSortedByRole() {
        return facade.findByOrderByRole();
    }


}
