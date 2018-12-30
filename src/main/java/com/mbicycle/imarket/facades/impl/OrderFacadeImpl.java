package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.OrderProduct;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;

import com.mbicycle.imarket.daos.OrderProductRepository;
import com.mbicycle.imarket.utils.converters.Converter;

import com.mbicycle.imarket.facades.interfaces.OrderFacade;
import com.mbicycle.imarket.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class OrderFacadeImpl implements OrderFacade {

    @Autowired
    private OrderService service;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private Converter<Order, OrderDTO> converter;

    @Autowired
    private Converter<OrderDTO, Order> reverseConverter;

    @Autowired
    private Converter<ProfileDTO, Profile> profileConverter;

    @Override
    public boolean add(OrderDTO dto) {
        Order order = reverseConverter.convert(dto);
        List<OrderProduct> orderProducts = order.getOrderProducts();
        if (service.get(order.getProfile()) == null) {
            service.add(order);
            order = service.get(order.getProfile());
            for (OrderProduct o: order.getOrderProducts()) {
                o.setOrder(order);
                o = orderProductRepository.getOne(o.getId());
                orderProductRepository.save(o);
            }
        } else {
            order = service.get(order.getProfile());
            for (OrderProduct o: orderProducts) {
                o.setOrder(order);
            }
            order.getOrderProducts().addAll(orderProducts);
        }
        return service.update(order);
    }

    @Override
    public boolean update(OrderDTO dto) {
        return service.update(reverseConverter.convert(dto));
    }

    @Override
    public boolean delete(OrderDTO dto) {
        return service.delete(reverseConverter.convert(dto));
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