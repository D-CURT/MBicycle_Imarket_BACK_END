package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.OrderDTO;
import com.mbicycle.imarket.facades.interfaces.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    @SuppressWarnings("ALL")
    private OrderFacade facade;

    @GetMapping("/orders/allOrders")
    public List<OrderDTO> getAll(){
        return facade.getAll();
    }

    @PostMapping("/orders/open")
    public ResponseEntity add(@RequestBody OrderDTO orderDTO){
        return facade.add(orderDTO) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
