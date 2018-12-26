package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.daos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }
}
