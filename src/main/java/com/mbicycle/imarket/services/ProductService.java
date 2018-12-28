package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    boolean add(Product product);

    Product get(String name);

    List<Product> findByOrderByName();

    List<Product> findByOrderByPrice();

    List<Product> findByGroupOrderByName(String name);

    List<Product> findByGroupOrderByPrice(String name);

    List<Product> findByNameLikeOrderByName(String name);

    List<Product> findByNameLikeAndStoreStatusIsTrue(String name);

    List<Product> findByNameLikeAndDiscountIsNotNull(String name);

    List<Product> findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(String name);

    boolean delete(Product product);
}
