package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.daos.GroupRepository;
import com.mbicycle.imarket.services.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class GroupServiceImpl implements GroupService {

    @Autowired
    @SuppressWarnings("ALL")
    private GroupRepository repository;

    @Override
    public boolean add(Group group) {

        String name = group.getName();
        if (get(name) == null) {
            repository.save(group);
        }
        return get(name) != null;
    }

    @Override
    public Group get(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Group> findByOrderByName() {
        return repository.findByOrderByNameAsc();
    }

    @Override
    public boolean delete(Group group) {
        String name = group.getName();
        if (get(name) != null) {
            repository.delete(group);
        }
        return get(name) == null;
    }
}
