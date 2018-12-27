package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.daos.GroupRepository;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
@SuppressWarnings("ALL")
public interface ProductService {

    public void addProduct(Product product);

    public Product getProduct(String name) ;

    public List<Product> findByOrderByName() ;

    public List<Product> findByOrderByPrice() ;

    public List<Product> findByGroupOrderByName(String name) ;

    public List<Product> findByGroupOrderByPrice(String name) ;

    public List<Product> findByNameLikeOrderByName(String name) ;

    public List<Product> findByNameLikeAndStoreStatusIsTrue(String name) ;

    public List<Product> findByNameLikeAndDiscountIsNotNull(String name) ;

    public List<Product> findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(String name) ;

    public Group groupByName(String name) ;
}
