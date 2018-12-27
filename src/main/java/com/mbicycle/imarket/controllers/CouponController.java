package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.CouponDTO;
import com.mbicycle.imarket.facades.interfaces.CouponFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CouponController {
    @Autowired
    @SuppressWarnings("ALL")
    private CouponFacade couponFacade;

    @GetMapping("/coupons/allCoupons")
    public List<CouponDTO> getAllCoupons(){
        return couponFacade.findAll();
    }

    @PostMapping("/coupons/addCoupon")
    public void addCoupon(@RequestBody CouponDTO couponDTO){
        couponFacade.add(couponDTO);
    }

    @GetMapping("/coupons/delete")
    public void deleteCoupon(CouponDTO couponDTO){
        couponFacade.delete(couponDTO);
    }
}
