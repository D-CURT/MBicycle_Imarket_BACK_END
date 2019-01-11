package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.OrderProduct;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.daos.OrderProductRepository;
import com.mbicycle.imarket.daos.OrderRepository;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

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
        Order orderInDb = orderRepository.getOne(order.getId());
        if(order.getDateClosed() != null)
            orderInDb.setDateClosed(order.getDateClosed());
        if(order.getDateGot() != null)
            orderInDb.setDateGot(order.getDateGot());
        if(order.getDateOpened() != null)
            orderInDb.setDateOpened(order.getDateOpened());
        if(order.getDatePaid() != null)
            orderInDb.setDatePaid(order.getDatePaid());
        if(order.getDateReady() != null)
            orderInDb.setDateReady(order.getDateReady());
        if(order.getDateSent() != null)
            orderInDb.setDateSent(order.getDateSent());
        if(order.getDelivery() != null)
            orderInDb.setDelivery(order.getDelivery());
        if(order.getPayment() != null)
            orderInDb.setPayment(order.getPayment());
        save(orderInDb);
        return orderRepository.getOne(order.getId()) != null;
    }

    @Override
    public boolean managing_update(Order order) {
        Order orderInDb = orderRepository.getOne(order.getId());
        if(order.getDateClosed() != null)
            orderInDb.setDateClosed(order.getDateClosed());
        if(order.getDateGot() != null)
            orderInDb.setDateGot(order.getDateGot());
        if(order.getDateOpened() != null)
            orderInDb.setDateOpened(order.getDateOpened());
        if(order.getDatePaid() != null)
            orderInDb.setDatePaid(order.getDatePaid());
        if(order.getDateReady() != null)
            orderInDb.setDateReady(order.getDateReady());
        if(order.getDateSent() != null)
            orderInDb.setDateSent(order.getDateSent());
        save(orderInDb);
        return orderRepository.getOne(order.getId()) != null;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByProfile(Profile profile) {
        return orderRepository.findByProfile(profile);
    }

//    @Override
//    public boolean delete(Order order) {
//        Order initial;
//        if ((initial = findByProfile(order.getProfile())) != null) {
//            orderRepository.delete(initial);
//        }
//        return findByProfile(order.getProfile()) == null;
//    }

    @Override
    public boolean cart_deleteOrderProduct(Order emptyOrder, List<Integer> ids) {
        Order order = cart_findByProfile(emptyOrder.getProfile());
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

    @Override
    public Order cart_findByProfile(Profile profile) {
        return orderRepository.findByProfileAndDateOpenedIsNull(profile);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

}