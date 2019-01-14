package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.services.securities.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;
import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    @SuppressWarnings("ALL")
    private ProfileFacade profileFacade;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/allProfilesSortedByName")
    public ResponseEntity<List<ProfileDTO>> getAllProfilesSortedByName() {
        return entityWithContent(profileFacade.findByOrderByName());
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileDTO> get(@RequestBody(required = false) ProfileDTO dto) {
        return entityWithContent(profileFacade.get());
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity update(@RequestBody ProfileDTO dto) {
        return entityWithStatus(profileFacade.update(dto));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody ProfileDTO dto) {
        return entityWithStatus(profileFacade.add(dto));
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody ProfileDTO dto) {
        return entityWithStatus(profileFacade.delete(dto));
    }

    @PostMapping(value = "/updateRole", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateRole(@RequestBody ProfileDTO dto) {
        return entityWithStatus(profileFacade.updateRole(dto));
    }
}
