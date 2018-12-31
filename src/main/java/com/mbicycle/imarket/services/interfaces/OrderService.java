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

    Order findInitial(Profile profile);

    List<Order> getAll();

    List<Order> findByProfile(Profile profile);
}
