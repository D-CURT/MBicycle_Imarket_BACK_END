package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.dto.ProductDTO;
import com.mbicycle.imarket.facades.interfaces.ProductFacade;
import com.mbicycle.imarket.services.GroupService;
import com.mbicycle.imarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class ProductFacadeImpl implements ProductFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Converter<ProductDTO, Product> reverseProductConverter;

    @Autowired
    private Converter<Product, ProductDTO> productConverter;

    public boolean addProduct(ProductDTO productDTO, MultipartFile file) {

        if (productRepository.findByName(productDTO.getName()) == null) {
            String strPicture2Add = "default.jpg";
            if (!file.isEmpty()) {
                String strPath = "images";
                File newFile = new File(strPath);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
                Random random = new Random();
                String strRandomName = String.valueOf(random.nextInt(Integer.MAX_VALUE) + 1);
                strPicture2Add = strRandomName + ".jpg";
                try (FileOutputStream fos = new FileOutputStream(strPath + "\\" + strPicture2Add)) {
                    fos.write(file.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Product newProduct = this.reverseProductConverter.convert(productDTO);
            newProduct.setPicture(strPicture2Add);
            productService.addProduct(newProduct);
            return true;
        }
        return false;
    }

    @Override
    public ProductDTO getProduct(String name) {
        return productConverter.convert(productService.getProduct(name));
    }

    @Override
    public List<ProductDTO> findByOrderByName() {
        return productService.findByOrderByName().stream().map(productConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByOrderByPrice() {
        return productService.findByOrderByPrice().stream().map(productConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findByGroupOrderByName(String name) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productService.findByGroupOrderByName(name)) {
            ProductDTO productDTO = productConverter.convert(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findByGroupOrderByPrice(String name) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productService.findByGroupOrderByPrice(name)) {
            ProductDTO productDTO = productConverter.convert(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findByNameLikeOrderByName(String name) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productService.findByNameLikeOrderByName(name)) {
            ProductDTO productDTO = productConverter.convert(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findByNameLikeAndStoreStatusIsTrue(String name) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productService.findByNameLikeAndStoreStatusIsTrue(name)) {
            ProductDTO productDTO = productConverter.convert(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findByNameLikeAndDiscountIsNotNull(String name) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productService.findByNameLikeAndDiscountIsNotNull(name)) {
            ProductDTO productDTO = productConverter.convert(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(String name) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productService.findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(name)) {
            ProductDTO productDTO = productConverter.convert(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }
}
