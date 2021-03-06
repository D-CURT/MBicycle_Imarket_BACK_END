package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);

    List<Category> findByOrderByNameAsc();

    List<Category> findByOrderByGroupsNameAsc();
}
