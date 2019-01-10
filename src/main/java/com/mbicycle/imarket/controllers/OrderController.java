package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProductDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.facades.interfaces.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;
import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    @SuppressWarnings("ALL")
    private OrderFacade facade;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAll(){
        return entityWithContent(facade.getAll());
    }

    @PostMapping("/delete")
    public ResponseEntity delete(){
        return entityWithStatus(facade.delete(new OrderDTO()));
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody OrderDTO dto) {
        return entityWithStatus(facade.update(dto));
    }

    @PostMapping("/managing/update")
    public ResponseEntity managing_update(@RequestBody OrderDTO dto) {
        return entityWithStatus(facade.managing_update(dto));
    }

    @GetMapping("/getByProfile")
    public ResponseEntity<List<OrderDTO>> getByProfile(@RequestBody ProfileDTO profileDTO){
        return entityWithContent(facade.get(profileDTO));
    }

    @PostMapping("/cart/add")
    public ResponseEntity cart_add(@RequestBody OrderDTO dto) {
        return entityWithStatus(facade.cart_add(dto));
    }

    @PostMapping("/cart/deleteProduct")
    public ResponseEntity cart_deleteProduct(@RequestBody OrderDTO dto) {
        return entityWithStatus(facade.cart_deleteProduct(dto));
    }
    @GetMapping(value = "/cart/products")
    public ResponseEntity<List<ProductDTO>> cart_getProducts() {
        return entityWithContent(facade.cart_getProducts(new OrderDTO()));
    }
}
