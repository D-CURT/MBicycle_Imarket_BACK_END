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
    public Order get(Profile profile) {
        return orderRepository.findByProfile(profile);
    }

    @Override
    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    @Override
    public boolean update(Order orderToUpdate) {
        Order order = orderRepository.findByProfile(orderToUpdate.getProfile());
        if (order != null) {
            order.setDateClosed(orderToUpdate.getDateClosed());
            order.setDateGot(orderToUpdate.getDateGot());
            order.setDateOpened(orderToUpdate.getDateOpened());
            order.setDatePaid(orderToUpdate.getDatePaid());
            order.setDateReady(orderToUpdate.getDateReady());
            order.setDateSent(orderToUpdate.getDateSent());
            orderRepository.save(order);
        }
        return order.equals(orderToUpdate);
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
