package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.daos.GroupRepository;
import com.mbicycle.imarket.services.CategoryService;
import com.mbicycle.imarket.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("ALL")
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository repository;

    @Autowired
    private CategoryService categoryService;

    public boolean addGroup(String groupName, String categoryName) {
        Category category;
        
        categoryService.addCategory(categoryName);
        category = categoryService.getCategory(categoryName);

        Group group = new Group(groupName, category);

        if (repository.findByName(groupName) == null) {
            addGroup(group);
        }
        return true;
    }

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
