package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {
    @Autowired
    private ProfileService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/profiles/allProfilesSortedByName")
    public List<Profile> getAllProfilesSortedByName() {
        return service.findByOrderByName();
    }
}
