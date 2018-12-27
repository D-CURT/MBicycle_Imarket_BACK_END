package com.mbicycle.imarket.facades;

import com.mbicycle.imarket.dto.ProductDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface ProductFacade {
    boolean addProduct(ProductDTO productDTO, MultipartFile file);
}
