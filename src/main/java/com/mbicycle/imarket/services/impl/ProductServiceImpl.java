package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.services.interfaces.GroupService;
import com.mbicycle.imarket.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
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
    public Product addWithReturn(Product product) {
        String name = product.getName();
        if (get(name) == null) {
            return repository.save(product);
        }
        return null;
    }

    @Override
    public Product update(Product product) {
        Product productInDB;
        if ( (productInDB = get(product.getId())) != null ) {
            //Assuming that product.id already in product
            product.setPicture(product.getPicture() != null ? product.getPicture() : productInDB.getPicture());
            product.setName(product.getName() != null ? product.getName() : productInDB.getName());
            product.setDescriptionFull(product.getDescriptionFull() != null ? product.getDescriptionFull() : productInDB.getDescriptionFull());
            product.setDescriptionPreview(product.getDescriptionPreview() != null ? product.getDescriptionPreview() : productInDB.getDescriptionPreview());
            product.setDiscount(product.getDiscount() != null ? product.getDiscount() : productInDB.getDiscount());
            product.setPrice(product.getPrice() != null ? product.getPrice() : productInDB.getPrice());
            product.setStoreStatus(product.getStoreStatus() != null ? product.getStoreStatus() : productInDB.getStoreStatus());
            product.setGroup(product.getGroup() != null ? product.getGroup() : productInDB.getGroup());
            return repository.save(product);
        }
        return null;
    }

    @Override
    public Product get(Integer id) {
        return repository.getOne(id);
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
    public List<Product> findByStoreStatusIsFalseAndDiscountIsNullOrderByName()  {
        return repository.findByStoreStatusIsFalseAndDiscountIsNullOrderByNameAsc();
    }

    @Override
    public List<Product> findByStoreStatusIsTrueAndDiscountIsNullOrderByName()  {
        return repository.findByStoreStatusIsTrueAndDiscountIsNullOrderByNameAsc();
    }

    @Override
    public List<Product> findByStoreStatusIsFalseAndDiscountIsNotNullOrderByName()  {
        return repository.findByStoreStatusIsFalseAndDiscountIsNotNullOrderByNameAsc();
    }

    @Override
    public List<Product> findByStoreStatusIsTrueAndDiscountIsNotNullOrderByName()  {
        return repository.findByStoreStatusIsTrueAndDiscountIsNotNullOrderByNameAsc();
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
    public List<Product> findByNameLikeOrderByNameIgnoreCase(String name) {
        return repository.findByNameIgnoreCaseContainingOrderByNameAsc(name);
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
        if ((product = get(name)) != null) {
            new File("images/" + product.getPicture()).delete();
            repository.delete(product);
        }
        return get(name) == null;
    }

    private Group groupByName(String name) {
        return groupService.get(name);
    }
}
