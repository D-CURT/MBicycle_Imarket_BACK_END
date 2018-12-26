package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.daos.GroupRepository;
import com.mbicycle.imarket.daos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("ALL")
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private GroupRepository groupRepository;

    public boolean addProduct(String name, double price, String groupName) {
        Group group;
        if ((group = groupRepository.findByName(groupName)) == null) {
            return false;
        }

        Product product;
        if (repository.findByName(name) != null) {
            return false;
        }

        product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setGroup(group);

        addProduct(product);
        return true;
    }

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

    private Group groupByName(String name) {
        Group group = new Group();
        if (groupRepository.findByName(name) == null) {
            group.setName(name);
            groupRepository.save(group);
        }

        return groupRepository.findByName(name);
    }
}
