package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private GroupService service;

    @GetMapping("/groups/allGroupsSortedByName")
    public List<Group> getAllGroupsSortedByName() {
        return service.findByOrderByName();
    }
}
