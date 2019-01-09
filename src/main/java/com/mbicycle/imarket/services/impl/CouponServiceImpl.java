package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Coupon;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.daos.CouponRepository;
import com.mbicycle.imarket.services.interfaces.CouponService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CouponServiceImpl implements CouponService {
    @Autowired
    @SuppressWarnings("ALL")
    private CouponRepository repository;

    @Override
    public boolean add(Coupon coupon) {
        String description = coupon.getDescription();
        if (repository.findByDescription(description) == null) {
            repository.save(coupon);
        }
        return repository.findByDescription(description) != null;
    }

    @Override
    public boolean delete(Coupon coupon) {
        String description = coupon.getDescription();

        if ((coupon = repository.findByDescription(description)) != null) {
            repository.delete(coupon);
        }

        return repository.findByDescription(description) == null;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        boolean result = true;
        for (int id : ids) {
            repository.deleteById(id);
        }

        return result;
    }

    @Override
    public List<Coupon> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Coupon> findByProfile(Profile profile) {
        return repository.findByProfile(profile);
    }

}
