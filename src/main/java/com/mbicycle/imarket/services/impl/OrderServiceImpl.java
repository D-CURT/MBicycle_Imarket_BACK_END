package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.daos.OrderRepository;
import com.mbicycle.imarket.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Autowired
    @SuppressWarnings("ALL")
    private OrderRepository orderRepository;

    @Override
    public boolean add(Order order) {
        orderRepository.save(order);
        return true;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public Order get(Profile profile) {
        return orderRepository.findByProfile(profile);
    }

    @Override
    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    @Override
    public boolean delete(Order order) {
        orderRepository.delete(order);
        return true;
    }
}
