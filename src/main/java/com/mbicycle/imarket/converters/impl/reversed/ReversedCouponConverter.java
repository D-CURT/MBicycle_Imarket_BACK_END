package com.mbicycle.imarket.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.converters.AbstractConverter;
import com.mbicycle.imarket.dto.CouponDTO;
import com.mbicycle.imarket.services.CouponService;
import com.mbicycle.imarket.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReversedCouponConverter extends AbstractConverter<CouponDTO, Coupon> {
//    @Autowired
//    private ProfileService profileService;
    @Override
    public void convert(CouponDTO source, Coupon target) {
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setSum(source.getSum());
        //target.setProfile(profileService.findByUser(source.getProfile()));
    }
}
