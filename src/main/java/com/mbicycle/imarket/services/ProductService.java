package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.daos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public void addProduct(Product product) {
        repository.save(product);
    }

    public Product getProduct(String name) {
        return repository.findByName(name);
    }

    public List<Product> findByOrderByName() {
        return repository.findByOrderByNameAsc();
    }

    public List<Product> findByOrderByPrice() {
        return repository.findByOrderByPriceAsc();
    }

    public List<Product> findByGroupOrderByName(Group group) {
        return repository.findByGroupOrderByNameAsc(group);
    }

    public List<Product> findByGroupOrderByPrice(Group group) {
        return repository.findByGroupOrderByPriceAsc(group);
    }

    public List<Product> findByNameLikeOrderByName(String name) {
        return repository.findByNameLikeOrderByNameAsc(name);
    }

    public List<Product> findByNameLikeAndStoreStatusIsTrue(String name) {
        return repository.findByNameLikeAndStoreStatusIsTrueOrderByNameAsc(name);
    }

    public List<Product> findByNameLikeAndDiscountIsNotNull(String name) {
        return repository.findByNameLikeAndDiscountIsNotNullOrderByNameAsc(name);
    }

    public List<Product> findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(String name) {
        return repository.findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNullOrderByNameAsc(name);
    }


}
