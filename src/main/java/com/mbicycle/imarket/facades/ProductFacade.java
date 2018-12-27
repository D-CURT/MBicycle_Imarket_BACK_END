package com.mbicycle.imarket.facades;

import com.mbicycle.imarket.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ProductFacade {
    public boolean addProduct(ProductDTO productDTO, MultipartFile file);
}