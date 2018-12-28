package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.dto.OrderDTO;
import com.mbicycle.imarket.facades.interfaces.OrderFacade;
<<<<<<< HEAD
import com.mbicycle.imarket.services.OrderService;
=======
import com.mbicycle.imarket.services.interfaces.OrderService;
>>>>>>> afc5ef03b0cea2f3f0da9644efaeec8c1c395b1b
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderService service;

    @Autowired
    private Converter<Order, OrderDTO> converter;

    @Autowired
    private Converter<OrderDTO, Order> reverseConverter;

    @Override
    public boolean add(OrderDTO orderDTO) {
        return service.add(reverseConverter.convert(orderDTO));
    }

    @Override
    public boolean update(OrderDTO orderDTO) {
        return service.delete(reverseConverter.convert(orderDTO));
    }

    @Override
    public boolean delete(OrderDTO orderDTO) {
        return service.delete(reverseConverter.convert(orderDTO));
    }

    @Override
    public List<OrderDTO> getAll() {
        return service.getAll().stream().map(converter::convert).collect(Collectors.toList());
    }

}
