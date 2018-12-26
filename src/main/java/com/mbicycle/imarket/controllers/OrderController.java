package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("/orders/allOrders")
    public List<Order> getAllOrders(){
        return service.getAllOrder();
    }
}
