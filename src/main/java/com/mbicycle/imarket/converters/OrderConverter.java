package com.mbicycle.imarket.converters;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.dto.OrderDTO;

public class OrderConverter extends AbstractConverter<Order, OrderDTO> {

    @Override
    public void convert(Order order, OrderDTO orderDTO) {
        orderDTO.setId(order.getId());
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
