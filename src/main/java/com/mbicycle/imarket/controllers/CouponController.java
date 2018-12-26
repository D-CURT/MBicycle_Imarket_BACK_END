package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CouponController {
    @Autowired
    private CouponService service;

    @GetMapping("/coupons/allCoupons")
    public List<Coupon> getAllCoupons(){
        return service.findAll();
    }
}
