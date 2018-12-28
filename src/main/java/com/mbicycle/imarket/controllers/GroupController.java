package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.GroupDTO;
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
    private GroupFacade groupFacade;

    @GetMapping(MAPPING + "/allGroupsSortedByName")
    public List<GroupDTO> getAllGroupsSortedByName() {
        return groupFacade.findByOrderByName();
    }

    @GetMapping(MAPPING + "/getGroup")
    public GroupDTO get(@PathVariable String name) {
        return groupFacade.get(name);
    }

    @PostMapping(value = MAPPING + "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody GroupDTO groupDTO) {
        return entityWithStatus(groupFacade.add(groupDTO));
    }

    @PostMapping(value = MAPPING + "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody GroupDTO dto) {
        return entityWithStatus(groupFacade.delete(dto));
    }
}
