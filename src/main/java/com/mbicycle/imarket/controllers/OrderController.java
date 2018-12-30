package com.mbicycle.imarket.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbicycle.imarket.beans.dto.OrderDTO;
import com.mbicycle.imarket.beans.dto.ProfileDTO;


import com.mbicycle.imarket.facades.interfaces.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity add(@RequestBody OrderDTO dto) {
        return entityWithStatus(facade.add(dto));
    }

    @PostMapping(MAPPING + "/delete")
    public ResponseEntity delete(@RequestBody OrderDTO dto){
        return entityWithStatus(facade.delete(dto));
    }

    @PostMapping( MAPPING + "/update")
    public ResponseEntity update(@RequestBody OrderDTO dto) {
        return entityWithStatus(facade.update(dto));
    }

    @GetMapping(MAPPING + "/getByProfile")
    public OrderDTO getByProfile(@RequestBody ProfileDTO profileDTO){
        return facade.get(profileDTO);
    }
}
