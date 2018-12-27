package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Coupon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CouponService {
    boolean add(Coupon coupon);

    boolean delete(Coupon coupon);

    List<Coupon> findAll();
}
