package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.GroupDTO;
import com.mbicycle.imarket.facades.interfaces.GroupFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;
import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    @SuppressWarnings("ALL")
    private GroupFacade facade;

    @GetMapping("/allGroupsSortedByName")
    public ResponseEntity<List<GroupDTO>> getAllGroupsSortedByName() {
        return entityWithContent(facade.findByOrderByName());
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<GroupDTO> get(@PathVariable String name) {
        return entityWithContent(facade.get(name));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody GroupDTO groupDTO) {
        return entityWithStatus(facade.add(groupDTO));
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody GroupDTO dto) {
        return entityWithStatus(facade.delete(dto));
    }
}
