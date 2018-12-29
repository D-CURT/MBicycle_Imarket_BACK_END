package com.mbicycle.imarket.utils.controllers;

import com.mbicycle.imarket.beans.dto.CategoryDTO;
import com.mbicycle.imarket.facades.interfaces.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class CategoryController {
    private static final String MAPPING = "/categories";

    @SuppressWarnings("ALL")
    @Autowired
    private CategoryFacade facade;

    @GetMapping(MAPPING + "/allCategoriesSortedByName")
    public List<CategoryDTO> getAllCategoriesSortedByName() {
        return facade.findByOrderByName();
    }

    @GetMapping(MAPPING + "/getCategory/{name}")
    public CategoryDTO get(@PathVariable String name){
        return facade.get(name);
    }

    @PostMapping(value = MAPPING + "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody CategoryDTO categoryDTO) {
        return entityWithStatus(facade.add(categoryDTO));

    }

    @PostMapping(value = MAPPING + "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody CategoryDTO dto) {
        return entityWithStatus(facade.delete(dto));
    }
}
