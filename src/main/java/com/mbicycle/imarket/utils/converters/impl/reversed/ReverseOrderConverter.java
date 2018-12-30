package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.OrderProduct;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.services.interfaces.ProductService;
import com.mbicycle.imarket.services.interfaces.ProfileService;
import com.mbicycle.imarket.services.interfaces.UserService;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class ReverseOrderConverter extends AbstractConverter<OrderDTO, Order> {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProductService productService;

    @Override
    public void convert(OrderDTO source, Order target) {
        target.setId(source.getId());
        target.setDelivery(source.getDelivery());
        target.setPayment(source.getPayment());
        target.setDateOpened(source.getDateOpened());
        target.setDateClosed(source.getDateClosed());
        target.setDateGot(source.getDateGot());
        target.setDatePaid(source.getDatePaid());
        target.setDateReady(source.getDateReady());
        target.setDateSent(source.getDateSent());
        target.setProfile(profileService.get(new User(source.getUserLogin())));
        target.setOrderProducts(source.getOrderProducts()
                                      .stream()
                                      .map(s -> new OrderProduct(productService.get(s)))
                                      .collect(Collectors.toList()));
    }
}
