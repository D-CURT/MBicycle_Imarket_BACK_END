package com.mbicycle.imarket.beans.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
    private int id;
    private String name;
}
