package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.GroupDTO;
import com.mbicycle.imarket.facades.interfaces.GroupFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    @SuppressWarnings("ALL")
    private GroupFacade groupFacade;

    @GetMapping("/groups/allGroupsSortedByName")
    public List<GroupDTO> getAllGroupsSortedByName() {
        return groupFacade.findByOrderByName();
    }

    @GetMapping("/groups/getGroup")
    public GroupDTO get(@PathVariable String name) {
        return groupFacade.get(name);
    }

    @PostMapping(value = "/groups/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void add(@RequestBody GroupDTO groupDTO) {
        groupFacade.add(groupDTO);
    }
}
