package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.CategoryDTO;
import com.mbicycle.imarket.facades.interfaces.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryFacade facade;

    @GetMapping("/categories/allCategoriesSortedByName")
    public List<CategoryDTO> getAllCategoriesSortedByName() {
        return facade.findByOrderByName();
    }

    @GetMapping("/categories/getCategory/{name}")
    public CategoryDTO getCategory(@PathVariable String name){
        return facade.get(name);
    }

    @GetMapping(value = "/categories/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addCategory(@RequestBody CategoryDTO dto) {
        return facade.add(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}
