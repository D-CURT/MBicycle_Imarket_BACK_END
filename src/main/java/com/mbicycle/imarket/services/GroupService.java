package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.daos.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository repository;

    public void addGroup(Group group){
        repository.save(group);
    }

    public Group getGroup(String name){
        return repository.findByName(name);
    }

    public List<Group> findByOrderByName(){
        return repository.findByOrderByNameAsc();
    }
}
