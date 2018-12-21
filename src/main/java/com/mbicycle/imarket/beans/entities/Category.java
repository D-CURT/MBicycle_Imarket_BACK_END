package com.mbicycle.imarket.beans.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @SequenceGenerator(name = "categories_sequence_generator", sequenceName = "categories_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_sequence_generator")
    private int id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Group> groups;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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

}
