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
    private Converter<OrderDTO, Order> reversedConverter;

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
    public boolean update(OrderDTO dto) {
        Order order = reversedConverter.convert(dto);
        return service.update(order);
    }

    @Override
    public boolean managing_update(OrderDTO dto) {
        Order order = reversedConverter.convert(dto);
        return service.managing_update(order);
    }

//    @Override
//    public boolean delete(OrderDTO dto) {
//        return service.delete(reversedConverter.convert(dto));
//    }

    @Override
    public List<OrderDTO> getAll() {
        return service.getAll().stream().map(converter::convert).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> get(ProfileDTO profileDTO) {
        return service.findAllByProfile(profileConverter.convert(profileDTO))
                      .stream()
                      .map(converter::convert)
                      .collect(Collectors.toList());
    }

    @Override
    public boolean cart_add(OrderDTO dto) {
        Order order = reversedConverter.convert(dto);
        List<OrderProduct> orderProducts = order.getOrderProducts();
        if (getInitial(order.getProfile()) == null) {
            service.update(order);
            order = cart_fillList(order, order.getOrderProducts());
        } else {
            order = cart_fillList(order, orderProducts);
            order.getOrderProducts().addAll(orderProducts);
        }
        return service.update(order);
    }

    @Override
    public boolean cart_deleteProduct(OrderDTO dto) {
        return service.cart_deleteOrderProduct(reversedConverter.convert(dto), dto.getProductsIds());
    }

    @Override
    public List<ProductDTO> cart_getProducts(OrderDTO dto) {
        return getInitial(reversedConverter.convert(dto).getProfile())
        .getOrderProducts()
        .stream()
        .map(orderProduct -> productConverter.convert(orderProduct.getProduct()))
        .collect(Collectors.toList());
    }

    public Order getInitial(Profile profile) {
        return service.cart_findByProfile(profile);
    }

    private Order cart_fillList(Order order, List<OrderProduct> orderProducts) {
        order = getInitial(order.getProfile());
        for (OrderProduct o: orderProducts) {
            o.setOrder(order);
        }
        return order;
    }

}