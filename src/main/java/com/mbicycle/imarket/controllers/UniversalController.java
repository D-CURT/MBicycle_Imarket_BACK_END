package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.daos.*;
import com.mbicycle.imarket.services.CategoryService;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SuppressWarnings("All")
public class UniversalController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GroupRepository groupRepository;

//    @Autowired
//    private OrderRepository orderRepository;
//
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/users/allUsersSortedByLogin")
    public List<User> getAllUsersSortedByLogin() {
        return userService.list();
    }

    @GetMapping("profiles/allProfilesSortedByName")
    public List<Profile> getAllProfilesSortedByName() {
        return profileRepository.findByOrderByNameAsc();
    }

    @GetMapping("/roles/allRolesSortedByRole")
    public List<Role> getAllRolesSortedByRole() {
        return roleRepository.findByOrderByRoleAsc();
    }

    @GetMapping("/categories/allCategoriesSortedByName")
    public List<Category> getAllCategoriesSortedByName() {
        return categoryService.list();
    }

    @GetMapping("/groups/allGroupsSortedByName")
    public List<Group> getAllGroupsSortedByName() {
        return groupRepository.findByOrderByNameAsc();
    }

    @GetMapping("products/allProductsSortedByName")
    public List<Product> getAllProductsSortedByName() {
        return productRepository.findByOrderByNameAsc();
    }

    @GetMapping("products/allProductsSortedByPrice")
    public List<Product> getAllProductsSortedByPrice() {
        return productRepository.findByOrderByPriceAsc();
    }

    @GetMapping(value = "products/allProductsWithGroupSortedByName"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllProductsWithGroupSortedByName(@RequestBody Group group) {
        return productRepository.findByGroupOrderByNameAsc(group);
    }

    @GetMapping(value = "products/allProductsWithGroupSortedByPrice"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllProductsWithGroupSortedByPrice(@RequestBody Group group) {
        return productRepository.findByGroupOrderByPriceAsc(group);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLike/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllProductsSortedByNameWithNameLike(@PathVariable String name) {
        return productRepository.findByNameLikeOrderByNameAsc(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndTrueStoreStatus/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllSortedByNameWithNameLikeAndTrueStoreStatus(@PathVariable String name) {
        return productRepository.findByNameLikeAndStoreStatusIsTrueOrderByNameAsc(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllSortedByNameWithNameLikeAndDiscount(@PathVariable String name) {
        return productRepository.findByNameLikeAndDiscountIsNotNullOrderByNameAsc(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount(@PathVariable String name) {
        return productRepository.findByNameLikeAndStoreStatusIsTrueAndDiscountIsNotNullOrderByNameAsc(name);
    }
}
