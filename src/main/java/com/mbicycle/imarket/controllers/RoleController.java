package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.RoleDTO;
import com.mbicycle.imarket.facades.interfaces.RoleFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;

@RestController
public class RoleController {
    @Autowired
    @SuppressWarnings("ALL")
    private RoleFacade facade;

    @GetMapping("/roles/allRolesSortedByRole")
    public ResponseEntity<List<RoleDTO>> getAllRolesSortedByRole() {
        return entityWithContent(facade.findByOrderByRole());
    }

}
