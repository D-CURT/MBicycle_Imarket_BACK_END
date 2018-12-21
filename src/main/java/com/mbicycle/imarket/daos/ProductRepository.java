package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);
/*
    @Query("")
    List<Product> getAllSortedByName();

    @Query("")
    List<Product> getAllSortedByPrice();

    @Query("")
    List<Product> getAllWithGroupSortedByName(String group);

    @Query("")
    List<Product> getAllWithGroupSortedByPrice(String group);

    @Query("")
    List<Product> getAllSortedByNameWithNameLike(String name);

    @Query("")
    List<Product> getAllSortedByNameWithNameLikeAndTrueStoreStatus(String name);

    @Query("")
    List<Product> getAllSortedByNameWithNameLikeAndNotNullDiscount(String name);

    @Query("")
    List<Product> getAllSortedByNameWithNameLikeAndTrueStoreStatusAndNotNullDiscount(String name);*/

}
