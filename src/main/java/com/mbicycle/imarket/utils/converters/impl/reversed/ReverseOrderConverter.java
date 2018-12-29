package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.OrderDTO;

public class ReverseOrderConverter extends AbstractConverter<OrderDTO, Order> {

    @Override
    public void convert(OrderDTO orderDTO, Order order) {
        order.setId(orderDTO.getId());
        order.setDelivery(orderDTO.getDelivery());
        order.setPayment(orderDTO.getPayment());
        order.setDateOpened(orderDTO.getDateOpened());
        order.setDateClosed(orderDTO.getDateClosed());
        order.setDateGot(orderDTO.getDateGot());
        order.setDatePaid(orderDTO.getDatePaid());
        order.setDateReady(orderDTO.getDateReady());
        order.setDateSent(orderDTO.getDateSent());
    }
}
