package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.dto.CouponDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CouponFacade {
    boolean add(CouponDTO couponDTO);

    List<CouponDTO> findAll();

    boolean delete(CouponDTO couponDTO);
}
