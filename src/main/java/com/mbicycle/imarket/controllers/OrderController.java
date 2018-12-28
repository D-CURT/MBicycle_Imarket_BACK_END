package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.OrderDTO;
import com.mbicycle.imarket.facades.interfaces.OrderFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    @SuppressWarnings("ALL")
    private OrderFacade facade;

    @GetMapping("/orders/allOrders")
    public List<OrderDTO> getAll(){
        return facade.getAll();
    }

<<<<<<< HEAD
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
=======
    @PostMapping("/orders/open")
    public ResponseEntity add(@RequestBody OrderDTO orderDTO){
        return facade.add(orderDTO) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
>>>>>>> afc5ef03b0cea2f3f0da9644efaeec8c1c395b1b
    }
}
