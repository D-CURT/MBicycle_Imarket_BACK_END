package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.OrderProduct;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.daos.OrderProductRepository;
import com.mbicycle.imarket.daos.OrderRepository;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean add(Order order) {
        orderRepository.save(order);
        return findByProfile(order.getProfile()) != null;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByProfile(Profile profile) {
        return null;
    }

    @Override
    public boolean update(Order order) {
        add(order);
        return findInitial(order.getProfile()).equals(order);
    }

    @Override
    public Order findInitial(Profile profile) {
        return orderRepository.findByProfile(profile);
    }

    @Override
    public boolean delete(Order order) {
        Order initial;
        if ((initial = findInitial(order.getProfile())) != null) {
            orderRepository.delete(initial);
        }
        return findInitial(order.getProfile()) == null;
    }

    @Override
    public boolean deleteOrderProduct(Order emptyOrder, List<Integer> ids) {
        Order order = findInitial(emptyOrder.getProfile());
        List<OrderProduct> relations = new ArrayList<>();
        ids.forEach(integer -> {
            OrderProduct match = productRepository.getOne(integer).getOrderProducts()
                                                  .stream()
                                                  .filter(orderProduct -> orderProduct.getOrder().equals(order))
                                                  .findAny()
                                                  .orElse(null);
            if (match != null) {
                orderProductRepository.delete(match);
                relations.add(match);
            }
        });
        return relations.stream()
                        .anyMatch(orderProduct -> orderProductRepository.getOne(orderProduct.getId()) != null);
    }
}