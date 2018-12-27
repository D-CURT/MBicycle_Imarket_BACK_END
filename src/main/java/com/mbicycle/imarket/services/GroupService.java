package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {

   boolean addGroup(String groupName, String categoryName);

    Group getGroup(String name);

    List<Group> findByOrderByName();
}
