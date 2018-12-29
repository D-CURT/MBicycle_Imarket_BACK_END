package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.utils.converters.Converter;
import org.springframework.beans.factory.annotation.Autowired;

public class ReverseOrderConverter extends AbstractConverter<OrderDTO, Order> {

    @Autowired
    private ProfileFacade facade;

    @Autowired
    private Converter<ProfileDTO, Profile> converter;

    @Override
    public void convert(OrderDTO orderDTO, Order order) {
        order.setId(orderDTO.getId());
        order.setProfile(converter.convert(facade.fingByName(orderDTO.getProfile())));
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
