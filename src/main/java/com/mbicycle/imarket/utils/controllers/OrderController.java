package com.mbicycle.imarket.utils.controllers;

<<<<<<< HEAD:src/main/java/com/mbicycle/imarket/utils/controllers/OrderController.java
import com.mbicycle.imarket.dto.OrderDTO;
import com.mbicycle.imarket.dto.ProfileDTO;
=======
import com.mbicycle.imarket.beans.dto.OrderDTO;
>>>>>>> 6703bc28637e5ae04c375c3bd4f20c186aec383b:src/main/java/com/mbicycle/imarket/controllers/OrderController.java
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
    public ResponseEntity add(OrderDTO dto){
        return entityWithStatus(facade.add(dto));
    }

    @GetMapping(MAPPING + "/delete")
    public ResponseEntity delete(OrderDTO dto){
        return entityWithStatus(facade.delete(dto));
    }

    @GetMapping( MAPPING + "/update")
    public ResponseEntity update(OrderDTO dto) {
        return entityWithStatus(facade.update(dto));
    }

    @GetMapping(MAPPING + "/getByProfile")
    public OrderDTO getByProfile(ProfileDTO profileDTO){
        return facade.get(profileDTO);
    }
}
