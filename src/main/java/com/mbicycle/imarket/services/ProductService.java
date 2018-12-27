package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public interface ProductService {

    boolean addProduct(String name, double price, String groupName, String categoryName);

    boolean addProduct(String name, double price, String descriptionPreview
            , int discount, MultipartFile file
            , String groupName, String categoryName) throws FileNotFoundException;

    void addProduct(Product product);

    Product getProduct(String name);

    List<Product> findByOrderByName();

    List<Product> findByOrderByPrice();

    List<Product> findByGroupOrderByName(String name);

    List<Product> findByGroupOrderByPrice(String name);

    List<Product> findByNameLikeOrderByName(String name);

    List<Product> findByNameLikeAndStoreStatusIsTrue(String name);

    List<Product> findByNameLikeAndDiscountIsNotNull(String name);

    List<Product> findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(String name);

    Group groupByName(String name);


}
