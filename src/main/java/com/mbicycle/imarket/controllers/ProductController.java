package com.mbicycle.imarket.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.dto.ProductDTO;
import com.mbicycle.imarket.facades.interfaces.ProductFacade;
import com.mbicycle.imarket.services.CategoryService;
import com.mbicycle.imarket.services.GroupService;
import com.mbicycle.imarket.services.ProductService;
import com.mbicycle.imarket.services.*;
import com.mbicycle.imarket.daos.*;
import com.mbicycle.imarket.services.securities.SecurityService;
import com.mbicycle.imarket.services.securities.UserSecurityService;
import com.mbicycle.imarket.services.securities.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @Autowired(required=false)
    private UserSecurityService userSecurityService;

    @Autowired(required=false)
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ProductFacade productFacade;

    @GetMapping("/products/allProductsSortedByName")
    public List<Product> getAllProductsSortedByName() {
        return productService.findByOrderByName();
    }

    @GetMapping("/products/allProductsSortedByPrice")
    public List<Product> getAllProductsSortedByPrice() {
        return productService.findByOrderByPrice();
    }

    @GetMapping(value = "/products/allProductsWithGroupSortedByName/{groupName}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllProductsWithGroupSortedByName(@PathVariable String groupName) {
        return productService.findByGroupOrderByName(groupName);
    }

    @GetMapping(value = "/products/allProductsWithGroupSortedByPrice/{groupName}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllProductsWithGroupSortedByPrice(@PathVariable String groupName) {
        return productService.findByGroupOrderByPrice(groupName);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLike/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllProductsSortedByNameWithNameLike(@PathVariable String name) {
        return productService.findByNameLikeOrderByName(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndTrueStoreStatus/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllSortedByNameWithNameLikeAndTrueStoreStatus(@PathVariable String name) {
        return productService.findByNameLikeAndStoreStatusIsTrue(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllSortedByNameWithNameLikeAndDiscount(@PathVariable String name) {
        return productService.findByNameLikeAndDiscountIsNotNull(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount(@PathVariable String name) {
        return productService.findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNull(name);
    }

    @PostMapping(value = "/categories/add/{name}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addCategory(@PathVariable String name) {
        categoryService.addCategory(name);
    }

    @PostMapping(value = "/groups/add/{groupName, categoryName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addGroup(@PathVariable String groupName, String categoryName) {
        groupService.addGroup(groupName, categoryName);
    }

    @PostMapping(value = "/products/add")
    public ResponseEntity addProduct(@RequestParam("data") String strDTO, @RequestParam("photo") MultipartFile file)
            throws IOException {
        ProductDTO productDTO = new ObjectMapper().readValue(strDTO,ProductDTO.class);  //Convert string param (json) into DTO
        return productFacade.addProduct(productDTO,file) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
