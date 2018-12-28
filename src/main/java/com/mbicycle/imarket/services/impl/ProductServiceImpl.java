package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.services.GroupService;
import com.mbicycle.imarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SuppressWarnings("ALL")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private GroupService groupService;

    @Override
    public boolean add(Product product) {
        String name = product.getName();
        if (get(name) == null) {
            repository.save(product);
        }
        return get(name) != null;
    }

    @Override
    public Product get(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Product> findByOrderByName() {
        return repository.findByOrderByNameAsc();
    }

    @Override
    public List<Product> findByOrderByPrice() {
        return repository.findByOrderByPriceAsc();
    }

    @Override
    public List<Product> findByGroupOrderByName(String name) {
        return repository.findByGroupOrderByNameAsc(groupByName(name));
    }

    @Override
    public List<Product> findByGroupOrderByPrice(String name) {
        return repository.findByGroupOrderByPriceAsc(groupByName(name));
    }

    @Override
    public List<Product> findByNameLikeOrderByName(String name) {
        return repository.findByNameContainingOrderByNameAsc(name);
    }

    @Override
    public List<Product> findByNameLikeAndStoreStatusIsTrue(String name) {
        return repository.findByNameContainingAndStoreStatusIsTrueOrderByNameAsc(name);
    }

    @Override
    public List<Product> findByNameLikeAndDiscountIsNotNull(String name) {
        return repository.findByNameContainingAndDiscountIsNotNullOrderByNameAsc(name);
    }

    @Override
    public List<Product> findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(String name) {
        return repository.findByNameContainingAndStoreStatusIsTrueAndDiscountIsNotNullOrderByNameAsc(name);
    }

    @Override
    public boolean delete(Product product) {
        String name = product.getName();
        if (get(name) != null) {
            repository.delete(product);
        }
        return get(name) == null;
    }

    private Group groupByName(String name) {
        return groupService.get(name);
    }
}
