package com.mbicycle.imarket.beans.dto;

import com.mbicycle.imarket.beans.entities.Group;

import java.util.List;
import java.util.Objects;

public class CategoryDTO {
    private int id;
    private String name;
    private List<Group> groups;

    public CategoryDTO() {
    }

    public CategoryDTO( String name, List<Group> groups) {
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
