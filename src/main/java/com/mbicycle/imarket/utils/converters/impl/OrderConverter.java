package com.mbicycle.imarket.utils.converters.impl;

import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.OrderProduct;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.utils.converters.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;


public class OrderConverter extends AbstractConverter<Order, OrderDTO> {

    @Autowired
    private Converter<Profile, ProfileDTO> converter;

    @Override
    public void convert(Order order, OrderDTO orderDTO) {
        orderDTO.setId(order.getId());
        orderDTO.setUserLogin(order.getProfile().getUser().getLogin());
        orderDTO.setProductsNames(order.getOrderProducts()
                .stream().map(orderProduct -> orderProduct.getProduct().getName()).collect(Collectors.toList()));
        orderDTO.setDelivery(order.getDelivery());
        orderDTO.setPayment(order.getPayment());
        orderDTO.setDateOpened(order.getDateOpened());
        orderDTO.setDateClosed(order.getDateClosed());
        orderDTO.setDateGot(order.getDateGot());
        orderDTO.setDatePaid(order.getDatePaid());
        orderDTO.setDateReady(order.getDateReady());
        orderDTO.setDateSent(order.getDateSent());
    }
}
