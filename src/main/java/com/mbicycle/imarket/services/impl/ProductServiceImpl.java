package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.daos.ProductRepository;
<<<<<<< HEAD
import com.mbicycle.imarket.services.ProductService;
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
=======
import com.mbicycle.imarket.services.GroupService;
import com.mbicycle.imarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("ALL")
>>>>>>> b3e63ba33cc5e2bbf9a7da6169d58d5bab60cfa1
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
<<<<<<< HEAD
    private GroupServiceImpl groupService;

    public boolean addProduct(String name, double price, String groupName, String categoryName) {
        groupService.addGroup(groupName, categoryName);

        Group group = groupService.getGroup(groupName);

        if (repository.findByName(name) == null) {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setGroup(group);

            addProduct(product);
        }
        return true;
    }

    public boolean addProduct(String name, double price, String descriptionPreview
                            , int discount, MultipartFile file
                            , String groupName, String categoryName) throws FileNotFoundException {

        groupService.addGroup(groupName, categoryName);

        Group group = groupService.getGroup(groupName);

        if (repository.findByName(name) == null) {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setGroup(group);

            if (file.isEmpty()) {
                product.setPicture("default.jpg");
            }
            else {
                String strPath = "images";
                File newFile = new File(strPath);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
                Random random = new Random();
                String strRandonName = String.valueOf(random.nextInt(Integer.MAX_VALUE) + 1);
                String strFileName = strRandonName+".jpg";
                try (FileOutputStream fos = new FileOutputStream(strPath+"\\"+strFileName)) {
                    fos.write(file.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                product.setPicture(strFileName);
            }

            addProduct(product);
        }
        return true;
    }
=======
    private GroupService groupService;
>>>>>>> b3e63ba33cc5e2bbf9a7da6169d58d5bab60cfa1

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

<<<<<<< HEAD
    private Group groupByName(String name) {
=======
    public Group groupByName(String name) {
>>>>>>> b3e63ba33cc5e2bbf9a7da6169d58d5bab60cfa1
        /*Group group = new Group();
        if (groupRepository.findByName(name) == null) {
            group.setName(name);
            groupRepository.save(group);
        }*/

        return groupService.getGroup(name);
    }
}
