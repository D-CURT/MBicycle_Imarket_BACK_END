package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.GroupDTO;
import com.mbicycle.imarket.facades.interfaces.GroupFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(MAPPING + "/allGroupsSortedByName")
    public List<GroupDTO> getAllGroupsSortedByName() {
        return facade.findByOrderByName();
    }

<<<<<<< HEAD
    @GetMapping("/groups/getGroup/{name}")
=======
    @GetMapping(MAPPING + "/getGroup")
>>>>>>> afc5ef03b0cea2f3f0da9644efaeec8c1c395b1b
    public GroupDTO get(@PathVariable String name) {
        return facade.get(name);
    }

<<<<<<< HEAD
    @PostMapping(value = "/groups/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody GroupDTO dto) {
        return facade.add(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
=======
    @PostMapping(value = MAPPING + "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody GroupDTO groupDTO) {
        return entityWithStatus(groupFacade.add(groupDTO));
    }

    @PostMapping(value = MAPPING + "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody GroupDTO dto) {
        return entityWithStatus(groupFacade.delete(dto));
>>>>>>> afc5ef03b0cea2f3f0da9644efaeec8c1c395b1b
    }
}
