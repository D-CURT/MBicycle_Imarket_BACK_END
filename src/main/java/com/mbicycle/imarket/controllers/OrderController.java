package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.OrderDTO;
import com.mbicycle.imarket.facades.interfaces.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderFacade facade;

    @GetMapping("/orders/allOrders")
    public List<OrderDTO> getAllOrders(){
        return facade.getAll();
    }

    @PostMapping("/orders/add")
    public ResponseEntity addOrder(OrderDTO dto){
        return facade.add(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/order/delete")
    public ResponseEntity deleteOrder(OrderDTO dto){
        return facade.delete(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/order/update")
    public ResponseEntity updateOrder(OrderDTO dto){
       return facade.update(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
