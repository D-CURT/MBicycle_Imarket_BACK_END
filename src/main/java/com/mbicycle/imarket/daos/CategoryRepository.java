package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
