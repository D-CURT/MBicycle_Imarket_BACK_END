package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.daos.CouponRepository;
import com.mbicycle.imarket.dto.CouponDTO;
import com.mbicycle.imarket.facades.interfaces.CouponFacade;
import com.mbicycle.imarket.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;

public class CouponFacadeImpl implements CouponFacade {
    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private Converter<CouponDTO, Coupon> reverseCouponConverter;

    @Override
    public boolean addCoupon(CouponDTO couponDTO) {
        if (couponRepository.findByDescription(couponDTO.getDescription())==null){
            Coupon coupon = reverseCouponConverter.convert(couponDTO);
            couponService.addCoupon(couponDTO.getDescription(),couponDTO.getSum());
            return true;
        }
        return false;
    }
}
