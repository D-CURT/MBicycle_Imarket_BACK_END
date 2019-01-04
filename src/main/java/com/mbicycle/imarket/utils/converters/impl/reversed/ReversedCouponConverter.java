package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.services.interfaces.ProfileService;
import com.mbicycle.imarket.services.interfaces.UserService;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.CouponDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class ReversedCouponConverter extends AbstractConverter<CouponDTO, Coupon> {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @Override
    public void convert(CouponDTO source, Coupon target) {
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setSum(source.getSum());

        String login = source.getLogin();
        if (login != null) { // when the coupon is creating, the login will be null
            target.setProfile(profileService.get(userService.get(login)));
        }

    }
}
