package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.CouponDTO;
import com.mbicycle.imarket.facades.interfaces.CouponFacade;
import com.mbicycle.imarket.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;
import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class CouponController {
    private static final String MAPPING = "/coupons";

    @Autowired
    @SuppressWarnings("ALL")
    private CouponFacade facade;

    @GetMapping(value = MAPPING + "/getAll")
    public ResponseEntity<List<CouponDTO>> getAll(){
        return entityWithContent(facade.getAll());
    }

    @GetMapping(MAPPING + "/get")
    public ResponseEntity<List<CouponDTO>> get(@RequestBody CouponDTO dto){
        return entityWithContent(facade.get(dto.getLogin()));
    }

    @PostMapping(MAPPING + "/add")
    public ResponseEntity add(@RequestBody CouponDTO couponDTO){
        return entityWithStatus(facade.add(couponDTO));
    }

//    @PostMapping(value = MAPPING + "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)// descriontion
//    public ResponseEntity delete(@RequestBody CouponDTO couponDTO){
//        return entityWithStatus(facade.delete(couponDTO));
//    }

    @PostMapping(value = MAPPING + "/deleteAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)// descriontion
    public ResponseEntity delete(@RequestBody CouponDTO couponDTO){

        return entityWithStatus(facade.delete(couponDTO));
    }
}
