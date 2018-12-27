package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Group;

import java.util.List;

public interface GroupService {
    boolean addGroup(String groupName, String categoryName);

    void addGroup(Group group);

    Group getGroup(String name);

    List<Group> findByOrderByName();
}
