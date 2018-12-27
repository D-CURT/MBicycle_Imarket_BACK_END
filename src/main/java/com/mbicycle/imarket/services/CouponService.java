package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Coupon;

import java.util.List;

public interface CouponService {
    void addCoupon(Coupon coupon);

    void deleteCoupon(Coupon coupon);

    List<Coupon> findAll();
}
