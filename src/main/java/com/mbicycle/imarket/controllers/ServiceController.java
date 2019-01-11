package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.services.securities.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class ServiceController {

    @Autowired
    private SecurityServiceImpl securityService;

    @PostMapping(value = "/login")
    public ResponseEntity login() {
        if(securityService.findLoggedUser()!=null)
            return entityWithStatus(302);
        return entityWithStatus(401);       //Doesn't work = it never called (instead calling MyBasicAuthenticationEntryPoint.commence with 401)
    }

}
