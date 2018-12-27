package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.daos.CouponRepository;
import com.mbicycle.imarket.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository  repository;

    public void addCoupon(Coupon coupon){
        repository.save(coupon);
    }

    public void deleteCoupon(Coupon coupon){
        repository.delete(coupon);
    }

    public List<Coupon> findAll(){
        return repository.findAll();
    }

}
