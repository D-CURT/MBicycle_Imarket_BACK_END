package com.mbicycle.imarket.services.interfaces;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    boolean update(Order order);

    boolean managing_update(Order order);

    boolean delete(Order order);

    List<Order> getAll();

    Order findByProfile(Profile profile);

    List<Order> findAllByProfile(Profile profile);

    boolean cart_deleteOrderProduct(Order order, List<Integer> ids);
}
