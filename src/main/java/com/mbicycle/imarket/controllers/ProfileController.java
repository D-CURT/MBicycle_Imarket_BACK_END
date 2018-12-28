package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {
    private static final String MAPPING = "/profiles";

    @Autowired
    @SuppressWarnings("ALL")
    private ProfileFacade profileFacade;

    @GetMapping(MAPPING + "/allProfilesSortedByName")
    public List<ProfileDTO> getAllProfilesSortedByName() {
        return profileFacade.findByOrderByName();
    }

    @GetMapping(value = MAPPING + "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProfileDTO get(@RequestBody ProfileDTO dto) {
        return profileFacade.get(dto);
    }
}
