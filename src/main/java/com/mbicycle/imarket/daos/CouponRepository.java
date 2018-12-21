package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Coupon findByDescription(String description);
}
