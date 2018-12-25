package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.daos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
    @Autowired
    private CouponRepository  repository;

    public void addCoupon(Coupon coupon){
        repository.save(coupon);
    }



}
