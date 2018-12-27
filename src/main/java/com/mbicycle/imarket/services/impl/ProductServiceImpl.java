package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.services.GroupService;
import com.mbicycle.imarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private GroupService groupService;

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

    public List<Product> findByGroupOrderByName(String name) {
        return repository.findByGroupOrderByNameAsc(groupByName(name));
    }

    public List<Product> findByGroupOrderByPrice(String name) {
        return repository.findByGroupOrderByPriceAsc(groupByName(name));
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


    public Group groupByName(String name) {
        /*Group group = new Group();
        if (groupRepository.findByName(name) == null) {
            group.setName(name);
            groupRepository.save(group);
        }*/

        return groupService.getGroup(name);
    }
}
