package com.mbicycle.imarket.services.interfaces;

import com.mbicycle.imarket.beans.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    boolean add(Order order);

    boolean delete(Order order);

    List<Order> getAll();
}
