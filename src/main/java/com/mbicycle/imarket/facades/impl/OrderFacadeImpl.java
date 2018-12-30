package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;

import com.mbicycle.imarket.utils.converters.Converter;

import com.mbicycle.imarket.facades.interfaces.OrderFacade;
import com.mbicycle.imarket.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderService service;

    @Autowired
    private Converter<Order, OrderDTO> converter;

    @Autowired
    private Converter<OrderDTO, Order> reverseConverter;

    @Autowired
    private Converter<ProfileDTO, Profile> profileConverter;

    @Override
    public boolean add(OrderDTO orderDTO) {
        Order order = service.get(reverseConverter.convert(orderDTO).getProfile());
        return (order != null && order.getDateOpened() == null) ? service.update(order) : service.add(order);
    }

    @Override
    public boolean update(OrderDTO orderDTO) {
        return service.update(reverseConverter.convert(orderDTO));
    }

    @Override
    public boolean delete(OrderDTO orderDTO) {
        return service.delete(reverseConverter.convert(orderDTO));
    }

    @Override
    public List<OrderDTO> getAll() {
        return service.getAll().stream().map(converter::convert).collect(Collectors.toList());
    }

    @Override
    public OrderDTO get(ProfileDTO profileDTO) {
        return converter.convert(service.findByProfile(profileConverter.convert(profileDTO)));
    }
}
