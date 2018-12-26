package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Coupon findByDescription(String description);
    List<Coupon> findAll();

}
