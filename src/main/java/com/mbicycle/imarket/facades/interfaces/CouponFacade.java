package com.mbicycle.imarket.facades.interfaces;

import com.mbicycle.imarket.beans.dto.CouponDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CouponFacade {
    boolean add(CouponDTO couponDTO);

    List<CouponDTO> getAll();

    List<CouponDTO> get(String login);

    boolean deleteByIds(List<String> ids);
}
