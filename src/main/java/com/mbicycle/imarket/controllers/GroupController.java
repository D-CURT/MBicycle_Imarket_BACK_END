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
    private GroupFacade groupFacade;

    @GetMapping("/groups/allGroupsSortedByName")
    public List<GroupDTO> getAllGroupsSortedByName() {
        return groupFacade.findByOrderByName();
    }

    @GetMapping("/groups/getGroup")
    public GroupDTO getGroup(@PathVariable String name) {
        return groupFacade.getGroup(name);
    }

    @PostMapping(value = "/groups/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addGroup(@RequestBody GroupDTO groupDTO) {
        groupFacade.addGroup(groupDTO);
    }
}
