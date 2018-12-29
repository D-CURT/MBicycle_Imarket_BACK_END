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
        if (findByProfile(order.getProfile()) == null) {
            orderRepository.save(order);
        }
        return findByProfile(order.getProfile()) != null;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public boolean update(Order order) {
        Order order1 = order;
        if (orderRepository.findByProfile(order.getProfile()) != null){
            order1 = orderRepository.update(order);
        }
        return !order.equals(order1);
    }

    @Override
    public Order findByProfile(Profile profile) {
        return orderRepository.findByProfile(profile);
    }

    @Override
    public boolean delete(Order order) {
        if (findByProfile(order.getProfile()) != null) {
            orderRepository.delete(order);
        }
        return findByProfile(order.getProfile()) == null;
    }
}
