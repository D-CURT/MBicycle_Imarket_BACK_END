package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.GroupDTO;
import com.mbicycle.imarket.facades.interfaces.GroupFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    @SuppressWarnings("ALL")
    private GroupFacade facade;

    @GetMapping("/groups/allGroupsSortedByName")
    public List<GroupDTO> getAllGroupsSortedByName() {
        return facade.findByOrderByName();
    }

    @GetMapping("/groups/getGroup/{name}")
    public GroupDTO get(@PathVariable String name) {
        return facade.get(name);
    }

    @PostMapping(value = "/groups/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody GroupDTO dto) {
        return facade.add(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
