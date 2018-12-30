package com.mbicycle.imarket.controllers;


import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;


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

    @GetMapping(MAPPING + "/all")
    public List<OrderDTO> getAll(){
        return facade.getAll();
    }

    @PostMapping(MAPPING + "/add")
    public ResponseEntity add(OrderDTO dto){
        return entityWithStatus(facade.add(dto));
    }

    @PostMapping(MAPPING + "/delete")
    public ResponseEntity delete(OrderDTO dto){
        return entityWithStatus(facade.delete(dto));
    }

    @PostMapping( MAPPING + "/update")
    public ResponseEntity update(OrderDTO dto) {
        return entityWithStatus(facade.update(dto));
    }

    @GetMapping(MAPPING + "/getByProfile")
    public OrderDTO getByProfile(ProfileDTO profileDTO){
        return facade.get(profileDTO);
    }
}
