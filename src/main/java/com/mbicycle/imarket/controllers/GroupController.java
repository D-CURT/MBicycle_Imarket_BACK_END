package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.GroupDTO;
import com.mbicycle.imarket.facades.interfaces.GroupFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class GroupController {
    private static final String MAPPING = "/groups";

    @Autowired
    @SuppressWarnings("ALL")
    private GroupFacade facade;

    @GetMapping(MAPPING + "/{name}")
    public List<GroupDTO> getAllGroupsSortedByName(@PathVariable String name) {
        return facade.findByOrderByName();
    }

    @GetMapping(MAPPING + "/getGroup/{name}")
    public GroupDTO get(@PathVariable String name) {
        return facade.get(name);
    }

    @PostMapping(value = MAPPING + "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody GroupDTO groupDTO) {
        return entityWithStatus(facade.add(groupDTO));
    }

    @PostMapping(value = MAPPING + "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody GroupDTO dto) {
        return entityWithStatus(facade.delete(dto));
    }
}
