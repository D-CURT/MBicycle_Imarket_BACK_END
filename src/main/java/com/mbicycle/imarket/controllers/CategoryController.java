package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.CategoryDTO;
import com.mbicycle.imarket.facades.interfaces.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryFacade categoryFacade;

    @GetMapping("/categories/allCategoriesSortedByName")
    public List<CategoryDTO> getAllCategoriesSortedByName() {
        return categoryFacade.findByOrderByName();
    }

    @GetMapping("/categories/getCategory")
    public CategoryDTO getCategory(@PathVariable String name){
        return categoryFacade.getCategoryDTO(name);
    }

    @PostMapping(value = "/categories/add/{name}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addCategory(@RequestBody CategoryDTO categoryDTO) {
        if (categoryFacade.addCategory(categoryDTO)){

        }else{

        }
    }


}
