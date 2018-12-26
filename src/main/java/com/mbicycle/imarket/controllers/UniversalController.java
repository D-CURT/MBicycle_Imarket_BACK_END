package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SuppressWarnings("All")
public class UniversalController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GroupService groupService;

//    @Autowired
//    private OrderRepository orderRepository;
//
    @Autowired
    private ProductService productService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userService;

    @Autowired
    private CouponService couponService;

    @GetMapping("/users/allUsersSortedByLogin")
    public List<User> getAllUsersSortedByLogin() {
        return userService.findByOrderByLoginAsc();
    }

    @GetMapping("/profiles/allProfilesSortedByName")
    public List<Profile> getAllProfilesSortedByName() {
        return profileService.findByOrderByName();
    }

    @GetMapping("/roles/allRolesSortedByRole")
    public List<Role> getAllRolesSortedByRole() {
        return roleService.findByOrderByRole();
    }

    @GetMapping("/categories/allCategoriesSortedByName")
    public List<Category> getAllCategoriesSortedByName() {
        return categoryService.findByOrderByName();
    }

    @GetMapping("/groups/allGroupsSortedByName")
    public List<Group> getAllGroupsSortedByName() {
        return groupService.findByOrderByName();
    }

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
}
