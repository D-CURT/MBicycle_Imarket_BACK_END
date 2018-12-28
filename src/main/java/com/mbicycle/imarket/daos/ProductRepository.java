package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    List<Product> findByOrderByNameAsc();

    List<Product> findByOrderByPriceAsc();

    List<Product> findByGroupOrderByNameAsc(Group group);

    List<Product> findByGroupOrderByPriceAsc(Group group);

    List<Product> findByNameContainingOrderByNameAsc(String name);

    List<Product> findByNameContainingAndStoreStatusIsTrueOrderByNameAsc(String name);

    List<Product> findByNameContainingAndDiscountIsNotNullOrderByNameAsc(String name);

    List<Product> findByNameContainingAndStoreStatusIsTrueAndDiscountIsNotNullOrderByNameAsc(String name);

}
