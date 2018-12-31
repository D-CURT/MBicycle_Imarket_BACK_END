package com.mbicycle.imarket.beans.dto;

import java.util.List;
import java.util.Objects;

public class CategoryGroupDTO {

    private String name;

    public CategoryGroupDTO() {
    }

    public CategoryGroupDTO(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
