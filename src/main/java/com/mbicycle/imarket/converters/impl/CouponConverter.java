package com.mbicycle.imarket.converters.impl;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.converters.AbstractConverter;
import com.mbicycle.imarket.dto.CouponDTO;

public class CouponConverter extends AbstractConverter<Coupon, CouponDTO> {
    @Override
    public void convert(Coupon source, CouponDTO target) {
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setSum(source.getSum());
        target.setProfile(source.getProfile().getName());
    }
}
