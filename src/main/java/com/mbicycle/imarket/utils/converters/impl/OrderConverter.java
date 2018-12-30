package com.mbicycle.imarket.utils.converters.impl;

import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;


public class OrderConverter extends AbstractConverter<Order, OrderDTO> {

    @Override
    public void convert(Order order, OrderDTO orderDTO) {
        orderDTO.setId(order.getId());
        orderDTO.setProfile(order.getProfile().getUser().getLogin());
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
