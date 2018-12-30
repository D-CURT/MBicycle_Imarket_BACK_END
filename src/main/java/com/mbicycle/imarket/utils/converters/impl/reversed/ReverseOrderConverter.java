package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.daos.OrderProductRepository;
import com.mbicycle.imarket.daos.OrderRepository;
import com.mbicycle.imarket.daos.ProductRepository;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import com.mbicycle.imarket.services.interfaces.ProfileService;
import com.mbicycle.imarket.services.interfaces.UserService;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.utils.converters.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class ReverseOrderConverter extends AbstractConverter<OrderDTO, Order> {

    @Autowired
    private ProfileFacade facade;

    @Autowired
    private ProfileService service;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderProductRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Converter<Profile, ProfileDTO> converter;

    @Autowired
    private Converter<ProfileDTO, Profile> reverseConverter;

    @Override
    public void convert(OrderDTO orderDTO, Order order) {
        order.setId(orderDTO.getId());
        order.setProfile(reverseConverter.convert(facade.get(converter.
                convert(service.get(userService.get(orderDTO.getUserLogin()))))));
        order.setOrderProducts(orderDTO.getProductsNames().stream().map(product-> repository.findByProduct(productRepository.findByName(product))).collect(Collectors.toList()));
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
