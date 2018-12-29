package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.utils.converters.Converter;
import com.mbicycle.imarket.beans.dto.CouponDTO;
import com.mbicycle.imarket.facades.interfaces.CouponFacade;
import com.mbicycle.imarket.services.interfaces.CouponService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class CouponFacadeImpl implements CouponFacade {
    @Autowired
    private CouponService couponService;

    @Autowired
    private Converter<Coupon, CouponDTO> couponConverter;

    @Autowired
    private Converter<CouponDTO, Coupon> reverseCouponConverter;

    @Override
    public boolean add(CouponDTO couponDTO) {
        return couponService.add(reverseCouponConverter.convert(couponDTO));
    }

    @Override
    public List<CouponDTO> findAll() {
        return couponService.findAll().stream().map(couponConverter::convert).collect(Collectors.toList());
    }

    @Override
    public boolean delete(CouponDTO couponDTO) {
        return couponService.delete(reverseCouponConverter.convert(couponDTO));
    }
}
