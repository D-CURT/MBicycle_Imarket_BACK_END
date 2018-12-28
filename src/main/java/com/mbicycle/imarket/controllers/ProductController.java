package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbicycle.imarket.dto.ProductDTO;
import com.mbicycle.imarket.facades.interfaces.ProductFacade;
import com.mbicycle.imarket.utils.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class ProductController {

    @Autowired
    @SuppressWarnings("ALL")
    private ProductFacade facade;

    @GetMapping("/products/allProductsSortedByName")
    public List<ProductDTO> getAllProductsSortedByName() {
        return facade.findByOrderByName();
    }

    @GetMapping("/products/allProductsSortedByPrice")
    public List<ProductDTO> getAllProductsSortedByPrice() {
        return facade.findByOrderByPrice();
    }

    @GetMapping(value = "/products/allProductsWithGroupSortedByName/{groupName}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDTO> getAllProductsWithGroupSortedByName(@PathVariable String groupName) {
        return facade.findByGroupOrderByName(groupName);
    }

    @GetMapping(value = "/products/allProductsWithGroupSortedByPrice/{groupName}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDTO> getAllProductsWithGroupSortedByPrice(@PathVariable String groupName) {
        return facade.findByGroupOrderByPrice(groupName);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLike/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDTO> getAllProductsSortedByNameWithNameLike(@PathVariable String name) {
        return facade.findByNameLikeOrderByName(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndTrueStoreStatus/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDTO> getAllSortedByNameWithNameLikeAndTrueStoreStatus(@PathVariable String name) {
        return facade.findByNameLikeAndStoreStatusIsTrue(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDTO> getAllSortedByNameWithNameLikeAndDiscount(@PathVariable String name) {
        return facade.findByNameLikeAndDiscountIsNotNull(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDTO> getAllSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount(@PathVariable String name) {
        return facade.findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(name);
    }

    @PostMapping(value = "/products/add")
    public ResponseEntity addProduct(@RequestParam("data") String strDTO, @RequestParam("photo") MultipartFile file)
            throws IOException {
        ProductDTO productDTO = new ObjectMapper().readValue(strDTO, ProductDTO.class);  //Convert string param (json) into DTO
<<<<<<< HEAD
        return facade.add(productDTO, file) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
=======
        return entityWithStatus(productFacade.add(productDTO, file));
>>>>>>> afc5ef03b0cea2f3f0da9644efaeec8c1c395b1b
    }
}
