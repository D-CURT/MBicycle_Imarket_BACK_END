package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.CouponDTO;
import com.mbicycle.imarket.facades.interfaces.CouponFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CouponController {
    @Autowired
    @SuppressWarnings("ALL")
    private CouponFacade facade;

    @GetMapping("/coupons/allCoupons")
    public List<CouponDTO> getAllCoupons(){
        return facade.findAll();
    }

    @PostMapping("/coupons/addCoupon")
    public ResponseEntity addCoupon(@RequestBody CouponDTO dto){
        return facade.add(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/coupons/delete")
    public ResponseEntity deleteCoupon(CouponDTO dto){
        return facade.delete(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
