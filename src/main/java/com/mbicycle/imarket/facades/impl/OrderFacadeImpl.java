package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.dto.ProductDTO;
import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.OrderProduct;
import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.services.interfaces.ProfileService;
import com.mbicycle.imarket.services.interfaces.UserService;
import com.mbicycle.imarket.services.securities.SecurityService;
import com.mbicycle.imarket.utils.converters.Converter;

import com.mbicycle.imarket.facades.interfaces.OrderFacade;
import com.mbicycle.imarket.services.interfaces.OrderService;
import com.mbicycle.imarket.utils.enums.OrderStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

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

    @Autowired
    private Converter<Product, ProductDTO> productConverter;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Override
    public boolean add(OrderDTO dto) {
        Order order = reverseConverter.convert(dto);
        List<OrderProduct> orderProducts = order.getOrderProducts();
        if (getInitial(order.getProfile()) == null) {
            service.add(order);
            order = fillList(order, order.getOrderProducts());
        } else {
            order = fillList(order, orderProducts);
            order.getOrderProducts().addAll(orderProducts);
        }
        return service.update(order);
    }

    @Override
    public boolean update(OrderDTO dto) {

        Order order = getInitial(reverseConverter.convert(dto).getProfile());
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


    public Order getInitial(Profile profile) {
        return service.findInitial((profile));
    }

    @Override
    public List<ProductDTO> getProducts(OrderDTO dto) {
        return getInitial(reverseConverter.convert(dto).getProfile())
        .getOrderProducts()
        .stream()
        .map(orderProduct -> productConverter.convert(orderProduct.getProduct()))
        .collect(Collectors.toList());
    }

    private Order fillList(Order order, List<OrderProduct> orderProducts) {
        order = getInitial(order.getProfile());
        for (OrderProduct o: orderProducts) {
            o.setOrder(order);
        }
        return order;
    }
}