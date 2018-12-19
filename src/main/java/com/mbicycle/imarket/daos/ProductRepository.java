package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
