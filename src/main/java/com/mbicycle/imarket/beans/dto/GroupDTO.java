package com.mbicycle.imarket.beans.dto;

import java.util.List;

public class GroupDTO {
    private int id;
    private String name;
    private String category;
    private List<String> products;

    public GroupDTO() {
    }

    public GroupDTO(String name, String category, List<String> products) {
        setName(name);
        setCategory(category);
        setProducts(products);
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
