package com.mbicycle.imarket.dto;

import java.util.List;

public class CategoryDTO {
    private int id;
    private String name;
    private List<String> groups;

    public CategoryDTO() {
    }

    public CategoryDTO( String name, List<String> groups) {
        setName(name);
        setGroups(groups);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
