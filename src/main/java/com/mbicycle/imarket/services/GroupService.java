package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Category;
import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.daos.CategoryRepository;
import com.mbicycle.imarket.daos.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("ALL")
public class GroupService {

    @Autowired
    private GroupRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public boolean addGroup(String groupName, String categoryName) {
        Category category;
        if ((category = categoryRepository.findByName(categoryName)) == null) {
            return false;
        }

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
