package com.mbicycle.imarket.facades;

import com.mbicycle.imarket.beans.entities.Group;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.dto.ProductDTO;
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

@Component
public class ProductFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ProductRepository productRepository;

    public boolean addProduct(ProductDTO productDTO) {

        groupService.addGroup(productDTO.getGroup(), "123");
        Group group = groupService.getGroup(productDTO.getGroup());

        if (productRepository.findByName(productDTO.getName()) == null) {

            MultipartFile file = productDTO.getPicture();
            String strPicture2Add = "default.jpg";

            if (!file.isEmpty())  {
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
            }

            Product product = new Product();

            // Move to converter:
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setGroup(group);

            product.setPicture(strPicture2Add);
            productService.addProduct(product);
        }

        return true;
    }
}
