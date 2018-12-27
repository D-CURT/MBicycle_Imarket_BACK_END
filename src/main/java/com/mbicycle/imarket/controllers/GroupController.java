package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/groups/allGroupsSortedByName")
    public List<Group> getAllGroupsSortedByName() {
        return groupService.findByOrderByName();
    }

    @PostMapping(value = "/groups/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addGroup(@PathVariable String groupName, String categoryName) {
        groupService.addGroup(groupName, categoryName);
    }
}
