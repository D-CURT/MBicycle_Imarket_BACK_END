package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
<<<<<<< HEAD
=======
import com.mbicycle.imarket.daos.GroupRepository;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
>>>>>>> b3e63ba33cc5e2bbf9a7da6169d58d5bab60cfa1
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;
<<<<<<< HEAD

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

=======
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
>>>>>>> b3e63ba33cc5e2bbf9a7da6169d58d5bab60cfa1
}
