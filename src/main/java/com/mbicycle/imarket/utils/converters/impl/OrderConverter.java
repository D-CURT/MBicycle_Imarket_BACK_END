package com.mbicycle.imarket.utils.converters.impl;

import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.OrderProduct;
import com.mbicycle.imarket.utils.converters.AbstractConverter;

import java.text.DateFormat;
import java.util.stream.Collectors;

public class OrderConverter extends AbstractConverter<Order, OrderDTO> {

    @Override
    public void convert(Order source, OrderDTO target) {
        target.setId(source.getId());
        target.setDelivery(source.getDelivery());
        target.setPayment(source.getPayment());

        DateFormat dateFormatter = DateFormat.getDateTimeInstance();
        if(source.getDateOpened() != null)
            target.setDateOpened(dateFormatter.format(source.getDateOpened()));
        if(source.getDateClosed() != null)
            target.setDateClosed(dateFormatter.format(source.getDateClosed()));
        if(source.getDateGot() != null)
            target.setDateGot(dateFormatter.format(source.getDateGot()));
        if(source.getDatePaid() != null)
            target.setDatePaid(dateFormatter.format(source.getDatePaid()));
        if(source.getDateReady() != null)
            target.setDateReady(dateFormatter.format(source.getDateReady()));
        if(source.getDateSent() != null)
            target.setDateSent(dateFormatter.format(source.getDateSent()));

        target.setProducts(source.getOrderProducts()
                                      .stream()
                                      .map(OrderProduct::getProduct)
                                      .collect(Collectors.toList()));
    }
}
