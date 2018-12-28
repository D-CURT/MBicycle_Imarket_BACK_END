package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.CouponDTO;
import com.mbicycle.imarket.facades.interfaces.CouponFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class CouponController {
    private static final String MAPPING = "/coupons";

    @Autowired
    @SuppressWarnings("ALL")
    private CouponFacade couponFacade;

    @GetMapping("/getAll")
    public List<CouponDTO> getAll(){
        return couponFacade.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody CouponDTO couponDTO){
        return entityWithStatus(couponFacade.add(couponDTO));
    }

    @PostMapping(value = MAPPING + "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(CouponDTO couponDTO){
        return entityWithStatus(couponFacade.delete(couponDTO));
    }
}
