package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.CategoryDTO;
import com.mbicycle.imarket.facades.interfaces.CategoryFacade;
import com.mbicycle.imarket.utils.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class CategoryController {
    private static final String MAPPING = "/categories";

    @Autowired
    @SuppressWarnings("ALL")
    private CategoryFacade categoryFacade;

    @GetMapping(MAPPING + "/allCategoriesSortedByName")
    public List<CategoryDTO> getAllCategoriesSortedByName() {
        return categoryFacade.findByOrderByName();
    }

    @GetMapping(MAPPING + "/getCategory")
    public CategoryDTO get(@PathVariable String name){
        return categoryFacade.get(name);
    }

    @PostMapping(value = MAPPING + "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody CategoryDTO categoryDTO) {
        return entityWithStatus(categoryFacade.add(categoryDTO));
    }

    @PostMapping(value = MAPPING + "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody CategoryDTO dto) {
        return entityWithStatus(categoryFacade.delete(dto));
    }
}
