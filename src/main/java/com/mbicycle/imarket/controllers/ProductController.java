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
@RequestMapping("/products")
public class ProductController {
    @Autowired
    @SuppressWarnings("ALL")
    private ProductFacade facade;

    @GetMapping("/allProductsSortedByName")
    public ResponseEntity<List<ProductDTO>> getAllProductsSortedByName() {
        return entityWithContent(facade.findByOrderByName());
    }

    @GetMapping("/allProductsSortedByPrice")
    public ResponseEntity<List<ProductDTO>> getAllProductsSortedByPrice() {
        return entityWithContent(facade.findByOrderByPrice());
    }

    @GetMapping("/allProductsWithStoreStatusIsFalseAndDiscountIsNullOrderByName")
    public ResponseEntity<List<ProductDTO>> findByStoreStatusIsFalseAndDiscountIsNullOrderByName() {
        return entityWithContent(facade.findByStoreStatusIsFalseAndDiscountIsNullOrderByName());
    }

    @GetMapping("/allProductsWithStoreStatusIsTrueAndDiscountIsNullOrderByName")
    public ResponseEntity<List<ProductDTO>> findByStoreStatusIsTrueAndDiscountIsNullOrderByName() {
        return entityWithContent(facade.findByStoreStatusIsTrueAndDiscountIsNullOrderByName());
    }

    @GetMapping("/allProductsWithStoreStatusIsFalseAndDiscountIsNotNullOrderByName")
    public ResponseEntity<List<ProductDTO>> findByStoreStatusIsFalseAndDiscountIsNotNullOrderByName() {
        return entityWithContent(facade.findByStoreStatusIsFalseAndDiscountIsNotNullOrderByName());
    }

    @GetMapping("/allProductsWithStoreStatusIsTrueAndDiscountIsNotNullOrderByName")
    public ResponseEntity<List<ProductDTO>> findByStoreStatusIsTrueAndDiscountIsNotNullOrderByName() {
        return entityWithContent(facade.findByStoreStatusIsTrueAndDiscountIsNotNullOrderByName());
    }

    @GetMapping(value = "/allProductsWithGroupSortedByName/{groupName}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProductsWithGroupSortedByName(@PathVariable String groupName) {
        return entityWithContent(facade.findByGroupOrderByName(groupName));
    }

    @GetMapping(value = "/allProductsWithGroupSortedByPrice/{groupName}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProductsWithGroupSortedByPrice(@PathVariable String groupName) {
        return entityWithContent(facade.findByGroupOrderByPrice(groupName));
    }

    @GetMapping(value = "/allProductsSortedByNameWithNameLike/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProductsSortedByNameWithNameLike(@PathVariable String name) {
        return entityWithContent(facade.findByNameLikeOrderByName(name));
    }

    @GetMapping(value = "/allProductsSortedByNameWithNameLikeIgnoreCase/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProductsSortedByNameWithNameLikeIgnoreCase(@PathVariable String name) {
        return entityWithContent(facade.findByNameLikeOrderByNameIgnoreCase(name));
    }

    @GetMapping(value = "/allProductsSortedByNameWithNameLikeAndTrueStoreStatus/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllSortedByNameWithNameLikeAndTrueStoreStatus(@PathVariable String name) {
        return entityWithContent(facade.findByNameLikeAndStoreStatusIsTrue(name));
    }

    @GetMapping(value = "/allProductsSortedByNameWithNameLikeAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllSortedByNameWithNameLikeAndDiscount(@PathVariable String name) {
        return entityWithContent(facade.findByNameLikeAndDiscountIsNotNull(name));
    }

    @GetMapping(value = "/allProductsSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount(@PathVariable String name) {
        return entityWithContent(facade.findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(name));
    }

    @GetMapping(value = "/getById/{id}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProductDTO> getById(@PathVariable Integer id) {
        return entityWithContent(facade.get(id));
    }

    @PostMapping(value = "/add")
    public ResponseEntity addProduct(@RequestParam("data") String strDTO, @RequestParam(name = "photo", required = false) MultipartFile file)
            throws IOException {
        ProductDTO productDTO = new ObjectMapper().readValue(strDTO, ProductDTO.class);  //Convert string param (json) into DTO
        return entityWithStatus(facade.add(productDTO, file));
    }

    @PostMapping(value = "/update")
    public ResponseEntity updateProduct(@RequestParam("data") String strDTO, @RequestParam(name = "photo", required = false) MultipartFile file)
            throws IOException {
        ProductDTO productDTO = new ObjectMapper().readValue(strDTO, ProductDTO.class);  //Convert string param (json) into DTO
        return entityWithStatus(facade.update(productDTO, file));
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody ProductDTO dto) {
        return entityWithStatus(facade.delete(dto));
    }
}
