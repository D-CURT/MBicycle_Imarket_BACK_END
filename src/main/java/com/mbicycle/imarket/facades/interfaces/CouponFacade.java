package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.dto.CouponDTO;
import org.springframework.stereotype.Component;

@Component
public interface CouponFacade {
    boolean addCoupon(CouponDTO couponDTO);
}
