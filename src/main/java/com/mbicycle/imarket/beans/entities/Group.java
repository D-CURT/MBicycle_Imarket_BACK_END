package com.mbicycle.imarket.beans.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    private int id;
    private String name;

    private Category category;
    private List<Product> products;
}
