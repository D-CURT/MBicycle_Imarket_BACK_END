package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(MAPPING + "/allProfilesSortedByName")
    public ResponseEntity<List<ProfileDTO>> getAllProfilesSortedByName() {
        return entityWithContent(profileFacade.findByOrderByName());
    }

    @GetMapping(value = MAPPING + "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProfileDTO> get(@RequestBody ProfileDTO dto) {
        return entityWithContent(profileFacade.get(dto));
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
