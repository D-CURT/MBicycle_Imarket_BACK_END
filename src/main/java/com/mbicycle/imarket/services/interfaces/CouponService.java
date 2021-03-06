package com.mbicycle.imarket.services.interfaces;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.beans.entities.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CouponService {
    boolean add(Coupon coupon);

    boolean deleteByIds(List<Integer> ids);

    boolean delete(Coupon coupon);

    List<Coupon> findAll();

    List<Coupon> findByProfile(Profile profile);

}
