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
    public boolean update(Order order) {
        //FIXME: A collection with cascade="all-delete-orphan" was no longer referenced by the owning entity instance: com.mbicycle.imarket.beans.entities.Order.orderProducts
        save(order);
        return orderRepository.getOne(order.getId()) != null;
    }

    @Override
    public boolean managing_update(Order order) {
        //FIXME: A collection with cascade="all-delete-orphan" was no longer referenced by the owning entity instance: com.mbicycle.imarket.beans.entities.Order.orderProducts
        save(order);
        return orderRepository.getOne(order.getId()) != null;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findByProfile(Profile profile) {
        //FIXME: Find another method to get + all usages + method FindInitial
        List<Order> orders = orderRepository.findByProfile(profile);
        if(orders != null && orders.size() == 1)
            return orders.get(0);
        return null;
    }

    @Override
    public List<Order> findAllByProfile(Profile profile) {
        return orderRepository.findByProfile(profile);
    }

    @Override
    public boolean delete(Order order) {
        Order initial;
        if ((initial = findByProfile(order.getProfile())) != null) {
            orderRepository.delete(initial);
        }
        return findByProfile(order.getProfile()) == null;
    }

    @Override
    public boolean cart_deleteOrderProduct(Order emptyOrder, List<Integer> ids) {
        Order order = findByProfile(emptyOrder.getProfile());
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


    public void save(Order order) {
        orderRepository.save(order);
    }
}