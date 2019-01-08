package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProductDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;


import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.facades.interfaces.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;
import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class OrderController {
    private static final String MAPPING = "/orders";

    @Autowired
    @SuppressWarnings("ALL")
    private OrderFacade facade;

    @GetMapping(MAPPING + "/all")
    public ResponseEntity<List<OrderDTO>> getAll(){
        return entityWithContent(facade.getAll());
    }

    @PostMapping(MAPPING + "/add")
    public ResponseEntity add(@RequestBody OrderDTO dto) {
        return entityWithStatus(facade.add(dto));
    }

    @PostMapping(MAPPING + "/delete")
    public ResponseEntity delete(){
        return entityWithStatus(facade.delete(new OrderDTO()));
    }

    @PostMapping(MAPPING + "/update")
    public ResponseEntity update(@RequestBody OrderDTO dto) {
        return entityWithStatus(facade.update(dto));
    }

    @PostMapping(MAPPING + "/deleteProduct")
    public ResponseEntity deleteProduct(@RequestBody OrderDTO dto) {
        return entityWithStatus(facade.deleteProduct(dto));
    }

    @GetMapping(MAPPING + "/getByProfile")
    public ResponseEntity<List<OrderDTO>> getByProfile(@RequestBody ProfileDTO profileDTO){
        return entityWithContent(facade.get(profileDTO));
    }

    @GetMapping(value = MAPPING + "/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return entityWithContent(facade.getProducts(new OrderDTO()));
    }
}
