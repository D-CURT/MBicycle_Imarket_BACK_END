package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.RoleDTO;
import com.mbicycle.imarket.facades.interfaces.RoleFacade;
import com.mbicycle.imarket.services.securities.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;

@RestController
@RequestMapping("/roles")
@SuppressWarnings("ALL")
public class RoleController {

    @Autowired
    private RoleFacade facade;

    @Autowired
    private SecurityServiceImpl securityService;

    @GetMapping("/allRolesSortedByRole")
    public ResponseEntity<List<RoleDTO>> getAllRolesSortedByRole() {
        return entityWithContent(facade.findByOrderByRole());
    }

    @GetMapping("/currentRole")
    public ResponseEntity<Collection<SimpleGrantedAuthority>> getCurrentRole() {
        Object o = securityService.findLoggedUser();
        System.out.println(securityService.getRoles());
        return entityWithContent(securityService.getRoles());
    }

}
