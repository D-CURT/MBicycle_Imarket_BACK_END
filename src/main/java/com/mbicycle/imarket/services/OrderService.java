package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
     void addOrder(Order order);

    List<Order> getAllOrder();
}
