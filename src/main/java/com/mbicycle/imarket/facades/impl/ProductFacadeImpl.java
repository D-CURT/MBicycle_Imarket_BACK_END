package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.converters.ProductConverter;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.dto.ProductDTO;
import com.mbicycle.imarket.facades.ProductFacade;
import com.mbicycle.imarket.services.GroupService;
import com.mbicycle.imarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;


public class ProductFacadeImpl implements ProductFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private Converter<ProductDTO, Product> reverseProductConverter;  //FIXME: Not working converter (Field required a bean of type that could not be found.)

//    @Autowired
//    private Converter<Product, ProductDTO> productConverter;

    public boolean addProduct(ProductDTO productDTO, MultipartFile file) {

        if (productRepository.findByName(productDTO.getName()) == null) {
            String strPicture2Add = "default.jpg";
            if (!file.isEmpty())  {
                String strPath = "images";
                File newFile = new File(strPath);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
                Random random = new Random();
                String strRandomName = String.valueOf(random.nextInt(Integer.MAX_VALUE) + 1);
                strPicture2Add = strRandomName+".jpg";
                try (FileOutputStream fos = new FileOutputStream(strPath+"\\"+strPicture2Add)) {
                    fos.write(file.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Product newProduct = new Product();

            //newProduct = this.reverseProductConverter.convert(productDTO); //FIXME: Not working converter (Field required a bean of type that could not be found.)



            //FIXME: Delete after converter starts working:
            groupService.addGroup(productDTO.getGroup(), "123");
            Group group = groupService.getGroup(productDTO.getGroup());
            newProduct.setName(productDTO.getName());
            newProduct.setGroup(group);



            newProduct.setPicture(strPicture2Add);
            productService.addProduct(newProduct);
            return true;
        }
        return false;
    }
}
