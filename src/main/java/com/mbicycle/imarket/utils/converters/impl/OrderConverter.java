package com.mbicycle.imarket.utils.converters.impl;

import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.utils.converters.AbstractConverter;

import java.util.stream.Collectors;

public class OrderConverter extends AbstractConverter<Order, OrderDTO> {

    @Override
    public void convert(Order source, OrderDTO target) {
        target.setId(source.getId());
        target.setDelivery(source.getDelivery());
        target.setPayment(source.getPayment());
        target.setDateOpened(source.getDateOpened());
        target.setDateClosed(source.getDateClosed());
        target.setDateGot(source.getDateGot());
        target.setDatePaid(source.getDatePaid());
        target.setDateReady(source.getDateReady());
        target.setDateSent(source.getDateSent());
        target.setUserLogin(source.getProfile().getUser().getLogin());
        target.setProductsIds(source.getOrderProducts()
                                      .stream()
                                      .map(orderProduct -> orderProduct.getProduct().getId())
                                      .collect(Collectors.toList()));
    }
}
