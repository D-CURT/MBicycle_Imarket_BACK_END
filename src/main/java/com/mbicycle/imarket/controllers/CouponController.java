package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.CouponDTO;
import com.mbicycle.imarket.facades.interfaces.CouponFacade;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
=======
import org.springframework.http.MediaType;
>>>>>>> afc5ef03b0cea2f3f0da9644efaeec8c1c395b1b
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class CouponController {
    private static final String MAPPING = "/coupons";

    @Autowired
    @SuppressWarnings("ALL")
    private CouponFacade facade;

<<<<<<< HEAD
    @GetMapping("/coupons/allCoupons")
    public List<CouponDTO> getAllCoupons(){
        return facade.findAll();
    }

    @PostMapping("/coupons/addCoupon")
    public ResponseEntity addCoupon(@RequestBody CouponDTO dto){
        return facade.add(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/coupons/delete")
    public ResponseEntity deleteCoupon(CouponDTO dto){
        return facade.delete(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
=======
    @GetMapping("/getAll")
    public List<CouponDTO> getAll(){
        return couponFacade.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody CouponDTO couponDTO){
        return entityWithStatus(couponFacade.add(couponDTO));
    }

    @PostMapping(value = MAPPING + "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(CouponDTO couponDTO){
        return entityWithStatus(couponFacade.delete(couponDTO));
>>>>>>> afc5ef03b0cea2f3f0da9644efaeec8c1c395b1b
    }
}
