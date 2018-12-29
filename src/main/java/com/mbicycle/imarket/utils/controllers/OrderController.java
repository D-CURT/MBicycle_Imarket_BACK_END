package com.mbicycle.imarket.utils.controllers;

import com.mbicycle.imarket.dto.OrderDTO;
import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.facades.interfaces.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class OrderController {
    private static final String MAPPING = "/orders";

    @Autowired
    @SuppressWarnings("ALL")
    private OrderFacade facade;

    @GetMapping(MAPPING + "/allOrders")
    public List<OrderDTO> getAll(){
        return facade.getAll();
    }

    @PostMapping(MAPPING + "/add")
    public ResponseEntity addOrder(OrderDTO dto){
        return entityWithStatus(facade.add(dto));
    }

    @GetMapping(MAPPING + "/delete")
    public ResponseEntity deleteOrder(OrderDTO dto){
        return entityWithStatus(facade.delete(dto));
    }

    @GetMapping( MAPPING + "/update")
    public ResponseEntity updateOrder(OrderDTO dto) {
        return entityWithStatus(facade.update(dto));
    }

    @GetMapping(MAPPING + "/getByProfile")
    public OrderDTO getByProfile(ProfileDTO profileDTO){
        return facade.get(profileDTO);
    }
}
