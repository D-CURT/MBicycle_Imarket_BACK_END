package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.OrderProduct;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.utils.converters.Converter;

import com.mbicycle.imarket.facades.interfaces.OrderFacade;
import com.mbicycle.imarket.services.interfaces.OrderService;
import com.mbicycle.imarket.utils.enums.OrderStatusType;
import org.springframework.beans.factory.annotation.Autowired;

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
    public boolean add(OrderDTO dto) {
        Order order = reverseConverter.convert(dto);
        List<OrderProduct> orderProducts = order.getOrderProducts();
        if (service.findInitial(order.getProfile()) == null) {
            service.add(order);
            order = service.findInitial(order.getProfile());
            for (OrderProduct o: order.getOrderProducts()) {
                o.setOrder(order);
            }
        } else {
            order = service.findInitial(order.getProfile());
            for (OrderProduct o: orderProducts) {
                o.setOrder(order);
            }
            order.getOrderProducts().addAll(orderProducts);
        }
        return service.update(order);
    }

    @Override
    public boolean update(OrderDTO dto) {
        Order order = service.findInitial(reverseConverter.convert(dto).getProfile());
        return service.update(OrderStatusType.status(dto).apply(dto, order));
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
    public List<OrderDTO> get(ProfileDTO profileDTO) {
        return service.findByProfile(profileConverter.convert(profileDTO))
                      .stream()
                      .map(converter::convert)
                      .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getInitial(ProfileDTO profileDTO) {
        return converter.convert(service.findInitial(profileConverter.convert(profileDTO)));
    }
}