package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.services.CategoryService;
import com.mbicycle.imarket.services.GroupService;
import com.mbicycle.imarket.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GroupService groupService;

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

    @PostMapping(value = "/products/addTest")
    public Product addProduct(@RequestParam("name") String name
            , @RequestParam("price") double price
            , @RequestParam("group") String group
            , @RequestParam("category") String category) throws FileNotFoundException {
        productService.addProduct(name, price, group, category);
        return productService.getProduct(name);
    }

    @PostMapping(value = "/products/add")
    public Product addProduct(@RequestParam("name") String name
                            , @RequestParam("price") double price
                            , @RequestParam("descriptionPreview") String descriptionPreview
                            , @RequestParam("discount") int discount
                            , @RequestParam("image") MultipartFile image
                            , @RequestParam("group") String group
                            , @RequestParam("category") String category) throws FileNotFoundException {
        productService.addProduct(name, price, descriptionPreview, discount, image, group, category);
        return productService.getProduct(name);
    }
}
