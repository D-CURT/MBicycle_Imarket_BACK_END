package com.mbicycle.imarket.services.interfaces;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    boolean add(Order order);

    boolean update(Order order);

    boolean delete(Order order);

    Order get(Profile profile);

    List<Order> getAll();

    Order findByProfile(Profile profile);
}
