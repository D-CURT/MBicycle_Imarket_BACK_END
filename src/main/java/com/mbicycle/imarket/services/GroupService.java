package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
    boolean add(Group group);

    Group get(String name);

    List<Group> findByOrderByName();

    boolean delete(Group group);
}
