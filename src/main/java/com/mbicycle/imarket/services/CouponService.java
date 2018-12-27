package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Coupon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CouponService {
    boolean addCoupon(String description, Integer sum);

    void deleteCoupon(Coupon coupon);

    List<Coupon> findAll();
}
