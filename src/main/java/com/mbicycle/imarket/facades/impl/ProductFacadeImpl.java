package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.dto.ProductDTO;
import com.mbicycle.imarket.facades.interfaces.ProductFacade;
import com.mbicycle.imarket.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class ProductFacadeImpl implements ProductFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Converter<ProductDTO, Product> reverseProductConverter;

    @Autowired
    private Converter<Product, ProductDTO> productConverter;

    @Override
    public boolean add(ProductDTO productDTO, MultipartFile file) {

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
            productService.add(newProduct);
            return true;
        }
        return false;
    }

    @Override
    public ProductDTO get(String name) {
        return productConverter.convert(productService.get(name));
    }

    @Override
    public List<ProductDTO> findByOrderByName() {
        return convert(productService.findByOrderByName());
    }

    @Override
    public List<ProductDTO> findByOrderByPrice() {
        return convert(productService.findByOrderByPrice());
    }

    @Override
    public List<ProductDTO> findByGroupOrderByName(String name) {
        return convert(productService.findByGroupOrderByName(name));
    }

    @Override
    public List<ProductDTO> findByGroupOrderByPrice(String name) {
        return convert(productService.findByGroupOrderByPrice(name));
    }

    @Override
    public List<ProductDTO> findByNameLikeOrderByName(String name) {
        return convert(productService.findByNameLikeOrderByName(name));
    }

    @Override
    public List<ProductDTO> findByNameLikeAndStoreStatusIsTrue(String name) {
        return convert(productService.findByNameLikeAndStoreStatusIsTrue(name));
    }

    @Override
    public List<ProductDTO> findByNameLikeAndDiscountIsNotNull(String name) {
        return convert(productService.findByNameLikeAndDiscountIsNotNull(name));
    }

    @Override
    public List<ProductDTO> findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(String name) {
        return convert(productService.findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(name));
    }

    private List<ProductDTO> convert(List<Product> products) {
        return products.stream().map(productConverter::convert).collect(Collectors.toList());
    }
}
