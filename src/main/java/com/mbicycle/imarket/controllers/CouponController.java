package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.dto.CouponDTO;
import com.mbicycle.imarket.facades.interfaces.CouponFacade;
import com.mbicycle.imarket.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CouponController {
    @Autowired
    private CouponFacade couponFacade;

    @GetMapping("/coupons/allCoupons")
    public List<CouponDTO> getAllCoupons(){
        return couponFacade.findAll();
    }

    @PostMapping("/coupons/addCoupon")
    public void addCoupon(@RequestBody CouponDTO couponDTO){
        couponFacade.addCoupon(couponDTO);
    }

    @GetMapping("/coupons/delete")
    public void deleteCoupon(CouponDTO couponDTO){
        couponFacade.deleteCoupon(couponDTO);
    }
}
