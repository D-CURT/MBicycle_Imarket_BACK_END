package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    @SuppressWarnings("ALL")
    private ProfileFacade profileFacade;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/profiles/allProfilesSortedByName")
    public List<ProfileDTO> getAllProfilesSortedByName() {
        return profileFacade.findByOrderByName();
    }
}
