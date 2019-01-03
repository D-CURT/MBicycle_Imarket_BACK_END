package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.RoleDTO;
import com.mbicycle.imarket.facades.interfaces.RoleFacade;
import com.mbicycle.imarket.services.securities.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
public class RoleController {
    @Autowired
    @SuppressWarnings("ALL")
    private RoleFacade facade;

    @Autowired
    private SecurityServiceImpl securityService;

    @GetMapping("/roles/allRolesSortedByRole")
    public List<RoleDTO> getAllRolesSortedByRole() {
        return facade.findByOrderByRole();
    }

    @GetMapping("/roles/currentRole")
    public Collection<SimpleGrantedAuthority> getCurrentRole() {
        return securityService.getRoles();
    }

}
