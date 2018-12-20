package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.daos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UniversalController {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/allUsersSortedByLogin")
    public List<User> getAllUsersSortedByLogin() {
        return userRepository.getAllSortedByLogin();
    }

    @GetMapping("profiles/allProfilesSortedByName")
    public List<Profile> getAllProfilesSortedByName() {
        return profileRepository.getAllSortedByName();
    }

    @GetMapping("/roles/allRolesSortedByRole")
    public List<Role> getAllRolesSortedByRole() {
        return roleRepository.getAllSortedByRole();
    }

    @GetMapping("/categories/allCategoriesSortedByName")
    public List<Category> getAllCategoriesSortedByName() {
        return categoryRepository.getAllSortedByName();
    }

    @GetMapping("/groups/allGroupsSortedByName")
    public List<Group> getAllGroupsSortedByName() {
        return groupRepository.getAllSortedByName();
    }

    @GetMapping("products/allProductsSortedByName")
    public List<Product> getAllProductsSortedByName() {
        return productRepository.getAllSortedByName();
    }

    @GetMapping("products/allProductsSortedByPrice")
    public List<Product> getAllProductsSortedByPrice() {
        return productRepository.getAllSortedByPrice();
    }

    @GetMapping(value = "products/allProductsWithGroupSortedByName/{group}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllProductsWithGroupSortedByName(@PathVariable String group) {
        return productRepository.getAllWithGroupSortedByName(group);
    }

    @GetMapping(value = "products/allProductsWithGroupSortedByPrice/{group}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllProductsWithGroupSortedByPrice(@PathVariable String group) {
        return productRepository.getAllWithGroupSortedByPrice(group);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLike/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllProductsSortedByNameWithNameLike(@PathVariable String name) {
        return productRepository.getAllSortedByNameWithNameLike(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndTrueStoreStatus/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllSortedByNameWithNameLikeAndTrueStoreStatus(@PathVariable String name) {
        return productRepository.getAllSortedByNameWithNameLikeAndTrueStoreStatus(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllSortedByNameWithNameLikeAndDiscount(@PathVariable String name) {
        return productRepository.getAllSortedByNameWithNameLikeAndNotNullDiscount(name);
    }

    @GetMapping(value = "/products/allProductsSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount/{name}"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getAllSortedByNameWithNameLikeAndTrueStoreStatusAndDiscount(@PathVariable String name) {
        return productRepository.getAllSortedByNameWithNameLikeAndTrueStoreStatusAndNotNullDiscount(name);
    }
}
