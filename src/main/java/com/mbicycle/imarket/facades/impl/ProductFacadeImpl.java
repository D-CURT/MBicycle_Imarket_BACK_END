package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.services.securities.SecurityService;
import com.mbicycle.imarket.utils.converters.Converter;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.beans.dto.ProductDTO;
import com.mbicycle.imarket.facades.interfaces.ProductFacade;
import com.mbicycle.imarket.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
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
    public ProductDTO get(String name) {
        return productConverter.convert(productService.get(name));
    }

    @Override
    public boolean add(ProductDTO productDTO, MultipartFile file) {
        File picFile = null;
        if (productRepository.findByName(productDTO.getName()) == null) {
            String strPicture2Add = "default.jpg";
            String strPath = "src\\main\\resources\\static\\assets\\images";
            String fullFilePath;
            if (!file.isEmpty()) {
                File newFile = new File(strPath);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
                Random random = new Random();
                String strRandomName = String.valueOf(random.nextInt(Integer.MAX_VALUE) + 1);
                strPicture2Add = strRandomName + ".jpg";
                fullFilePath = strPath + "\\" + strPicture2Add;
                try (FileOutputStream fos = new FileOutputStream(fullFilePath)) {
                    fos.write(file.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                picFile = new File(fullFilePath);
            }
            Product newProduct = this.reverseProductConverter.convert(productDTO);
            newProduct.setPicture(strPicture2Add);
            newProduct = productService.addWithReturn(newProduct);
            if(picFile!=null) {
                if(picFile.renameTo(new File(strPath + "\\" + String.valueOf(newProduct.getId())+".jpg")))
                {
                    newProduct.setPicture(String.valueOf(newProduct.getId())+".jpg");
                    productRepository.save(newProduct);
                    return true;
                }
                else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ProductDTO productDTO, MultipartFile file) {
        File picFile = null;
        Product productInDB = null;
        if(productDTO.getId()==null)
            return false;
        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
        if ( optionalProduct.isPresent() ) {
            productInDB = optionalProduct.get();
            String strPicture2Update = productInDB.getPicture();
            String strPath = "src\\main\\resources\\static\\assets\\images";
            String fullFilePath = strPath + "\\" + strPicture2Update ;
            if (file != null && !file.isEmpty()) {
                File newFile = new File(strPath);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
                if(newFile.exists())
                    newFile.delete();
                try (FileOutputStream fos = new FileOutputStream(fullFilePath)) {
                    fos.write(file.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Product updatedProduct = this.reverseProductConverter.convert(productDTO);
            if (productService.update(updatedProduct)!=null)
                return true;
        }
        return false;
    }

    @Override
    public boolean delete(ProductDTO productDTO) {
        return productService.delete(reverseProductConverter.convert(productDTO));
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
    public List<ProductDTO> findByStoreStatusIsFalseAndDiscountIsNullOrderByName() {
        return convert(productService.findByStoreStatusIsFalseAndDiscountIsNullOrderByName());
    }

    @Override
    public List<ProductDTO> findByStoreStatusIsTrueAndDiscountIsNullOrderByName() {
        return convert(productService.findByStoreStatusIsTrueAndDiscountIsNullOrderByName());
    }

    @Override
    public List<ProductDTO> findByStoreStatusIsFalseAndDiscountIsNotNullOrderByName() {
        return convert(productService.findByStoreStatusIsFalseAndDiscountIsNotNullOrderByName());
    }

    @Override
    public List<ProductDTO> findByStoreStatusIsTrueAndDiscountIsNotNullOrderByName() {
        return convert(productService.findByStoreStatusIsTrueAndDiscountIsNotNullOrderByName());
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
    public List<ProductDTO> findByNameLikeOrderByNameIgnoreCase(String name) {
        return convert(productService.findByNameLikeOrderByNameIgnoreCase(name));
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
