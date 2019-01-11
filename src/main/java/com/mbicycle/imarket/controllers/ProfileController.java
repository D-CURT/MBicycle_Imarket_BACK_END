package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.services.securities.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;
import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class ProfileController {
    private static final String MAPPING = "/profiles";

    @Autowired
    @SuppressWarnings("ALL")
    private ProfileFacade profileFacade;

    @Autowired
    private SecurityService securityService;

    @GetMapping(MAPPING + "/allProfilesSortedByName")
    public ResponseEntity<List<ProfileDTO>> getAllProfilesSortedByName() {
        return entityWithContent(profileFacade.findByOrderByName());
    }

    @GetMapping(MAPPING + "/customers")
    public ResponseEntity<List<ProfileDTO>> getAllCustomer() {
        return entityWithContent(profileFacade.getCustomers());
    }

    @GetMapping(value = MAPPING + "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileDTO> get(@RequestBody(required = false) ProfileDTO dto) {
        return entityWithContent(profileFacade.get(dto));
    }

    @PostMapping(value = MAPPING + "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity update(@RequestBody ProfileDTO dto) {
        return entityWithStatus(profileFacade.update(dto));
    }

    @PostMapping(value = MAPPING + "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody ProfileDTO dto) {
        return entityWithStatus(profileFacade.add(dto));
    }

    @PostMapping(value = MAPPING + "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody ProfileDTO dto) {
        return entityWithStatus(profileFacade.delete(dto));
    }
}
