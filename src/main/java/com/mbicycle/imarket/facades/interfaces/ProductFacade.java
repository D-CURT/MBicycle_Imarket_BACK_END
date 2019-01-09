package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.beans.dto.ProductDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface ProductFacade {
    boolean add(ProductDTO productDTO, MultipartFile file);

    boolean update(ProductDTO productDTO, MultipartFile file);

    ProductDTO get(String name);

    boolean delete(ProductDTO productDTO);

    List<ProductDTO> findByOrderByName();

    List<ProductDTO> findByOrderByPrice();

    List<ProductDTO> findByStoreStatusIsFalseAndDiscountIsNullOrderByName();

    List<ProductDTO> findByStoreStatusIsTrueAndDiscountIsNullOrderByName();

    List<ProductDTO> findByStoreStatusIsFalseAndDiscountIsNotNullOrderByName();

    List<ProductDTO> findByStoreStatusIsTrueAndDiscountIsNotNullOrderByName();

    List<ProductDTO> findByGroupOrderByName(String name);

    List<ProductDTO> findByGroupOrderByPrice(String name);

    List<ProductDTO> findByNameLikeOrderByName(String name);

    List<ProductDTO> findByNameLikeOrderByNameIgnoreCase(String name);

    List<ProductDTO> findByNameLikeAndStoreStatusIsTrue(String name);

    List<ProductDTO> findByNameLikeAndDiscountIsNotNull(String name);

    List<ProductDTO> findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(String name);
}
