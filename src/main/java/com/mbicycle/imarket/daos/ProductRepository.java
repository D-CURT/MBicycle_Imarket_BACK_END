package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    List<Product> findByOrderByNameAsc();

    List<Product> findByOrderByPriceAsc();

    //    False Null
    //    True Null
    //    False NotNull
    //    True NotNull
    List<Product> findByStoreStatusIsFalseAndDiscountIsNullOrderByNameAsc();

    List<Product> findByStoreStatusIsTrueAndDiscountIsNullOrderByNameAsc();

    List<Product> findByStoreStatusIsFalseAndDiscountIsNotNullOrderByNameAsc();

    List<Product> findByStoreStatusIsTrueAndDiscountIsNotNullOrderByNameAsc();

    List<Product> findByGroupOrderByNameAsc(Group group);

    List<Product> findByGroupOrderByPriceAsc(Group group);

    List<Product> findByNameContainingOrderByNameAsc(String name);

    List<Product> findByNameIgnoreCaseContainingOrderByNameAsc(String name);

    List<Product> findByNameContainingAndStoreStatusIsTrueOrderByNameAsc(String name);

    List<Product> findByNameContainingAndDiscountIsNotNullOrderByNameAsc(String name);

    List<Product> findByNameContainingAndStoreStatusIsTrueAndDiscountIsNotNullOrderByNameAsc(String name);

}
