package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.dto.OrderDTO;
import com.mbicycle.imarket.facades.OrderFacade;
import com.mbicycle.imarket.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private OrderFacade facade;

    @GetMapping("/orders/allOrders")
    public List<Order> getAllOrders(){
        return service.getAllOrder();
    }

    @PostMapping("/orders/open")
    public ResponseEntity openOrder(){
        //facade.openOrder(new OrderDTO());
        return new ResponseEntity(HttpStatus.OK);
    }
}
