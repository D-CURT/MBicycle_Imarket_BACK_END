package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.utils.converters.Converter;
import com.mbicycle.imarket.beans.dto.CouponDTO;
import com.mbicycle.imarket.facades.interfaces.CouponFacade;
import com.mbicycle.imarket.services.interfaces.CouponService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
    public List<CouponDTO> getAll() {
        return couponService.findAll().stream().map(couponConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<CouponDTO> get(String login) {
        return couponService.findAll().stream().map(couponConverter::convert).collect(Collectors.toList());
    }

    @Override
    public boolean deleteByIds(List<String> strIds) {

       List<Integer> ids = strIds.stream().map(strId -> Integer.parseInt(strId)).collect(Collectors.toList());

        return couponService.deleteByIds(ids);
    }
}
