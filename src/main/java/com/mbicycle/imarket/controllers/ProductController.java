package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbicycle.imarket.beans.dto.ProductDTO;
import com.mbicycle.imarket.facades.interfaces.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;
import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class ProductController {
    private static final String MAPPING = "/products";

    @Autowired
    @SuppressWarnings("ALL")
    private ProductFacade facade;

    @GetMapping(MAPPING + "/allProductsSortedByName")
    public ResponseEntity<List<ProductDTO>> getAllProductsSortedByName() {
        return entityWithContent(facade.findByOrderByName());
    }

    @GetMapping(MAPPING + "/allProductsSortedByPrice")
    public ResponseEntity<List<ProductDTO>> getAllProductsSortedByPrice() {
        return entityWithContent(facade.findByOrderByPrice());
    }

    @GetMapping(MAPPING + "/allProductsWithStoreStatusIsFalseAndDiscountIsNullOrderByName")
    public ResponseEntity<List<ProductDTO>> findByStoreStatusIsFalseAndDiscountIsNullOrderByName() {
        return entityWithContent(facade.findByStoreStatusIsFalseAndDiscountIsNullOrderByName());
    }

    @GetMapping(MAPPING + "/allProductsWithStoreStatusIsTrueAndDiscountIsNullOrderByName")
    public ResponseEntity<List<ProductDTO>> findByStoreStatusIsTrueAndDiscountIsNullOrderByName() {
        return entityWithContent(facade.findByStoreStatusIsTrueAndDiscountIsNullOrderByName());
    }

    @GetMapping(MAPPING + "/allProductsWithStoreStatusIsFalseAndDiscountIsNotNullOrderByName")
    public ResponseEntity<List<ProductDTO>> findByStoreStatusIsFalseAndDiscountIsNotNullOrderByName() {
        return entityWithContent(facade.findByStoreStatusIsFalseAndDiscountIsNotNullOrderByName());
    }

    @GetMapping(MAPPING + "/allProductsWithStoreStatusIsTrueAndDiscountIsNotNullOrderByName")
    public ResponseEntity<List<ProductDTO>> findByStoreStatusIsTrueAndDiscountIsNotNullOrderByName() {
        return entityWithContent(facade.findByStoreStatusIsTrueAndDiscountIsNotNullOrderByName());
    }

    @GetMapping(value = MAPPING + "/allProductsWithGroupSortedByName/{groupName}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProductsWithGroupSortedByName(@PathVariable String groupName) {
        return entityWithContent(facade.findByGroupOrderByName(groupName));
    }

    @GetMapping(value = MAPPING + "/allProductsWithGroupSortedByPrice/{groupName}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProductsWithGroupSortedByPrice(@PathVariable String groupName) {
        return entityWithContent(facade.findByGroupOrderByPrice(groupName));
    }

    @GetMapping(value = MAPPING + "/allProductsSortedByNameWithNameLike/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProductsSortedByNameWithNameLike(@PathVariable String name) {
        return entityWithContent(facade.findByNameLikeOrderByName(name));
    }

    @GetMapping(value = MAPPING + "/allProductsSortedByNameWithNameLikeIgnoreCase/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProductsSortedByNameWithNameLikeIgnoreCase(@PathVariable String name) {
        return entityWithContent(facade.findByNameLikeOrderByNameIgnoreCase(name));
    }

    @GetMapping(value = MAPPING + "/allProductsSortedByNameWithNameLikeAndTrueStoreStatus/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllSortedByNameWithNameLikeAndTrueStoreStatus(@PathVariable String name) {
        return entityWithContent(facade.findByNameLikeAndStoreStatusIsTrue(name));
    }

    @GetMapping(value = MAPPING + "/allProductsSortedByNameWithNameLikeAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllSortedByNameWithNameLikeAndDiscount(@PathVariable String name) {
        return entityWithContent(facade.findByNameLikeAndDiscountIsNotNull(name));
    }

    @GetMapping(value = MAPPING + "/allProductsSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount(@PathVariable String name) {
        return entityWithContent(facade.findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(name));
    }

    @PostMapping(value = MAPPING + "/add")
    public ResponseEntity addProduct(@RequestParam("data") String strDTO, @RequestParam(name = "photo", required = false) MultipartFile file)
            throws IOException {
        ProductDTO productDTO = new ObjectMapper().readValue(strDTO, ProductDTO.class);  //Convert string param (json) into DTO
        return entityWithStatus(facade.add(productDTO, file));
    }

    @PostMapping(value = MAPPING + "/update")
    public ResponseEntity updateProduct(@RequestParam("data") String strDTO, @RequestParam(name = "photo", required = false) MultipartFile file)
            throws IOException {
        ProductDTO productDTO = new ObjectMapper().readValue(strDTO, ProductDTO.class);  //Convert string param (json) into DTO
        return entityWithStatus(facade.update(productDTO, file));
    }

    @PostMapping(value = MAPPING + "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody ProductDTO dto) {
        return entityWithStatus(facade.delete(dto));
    }
}
