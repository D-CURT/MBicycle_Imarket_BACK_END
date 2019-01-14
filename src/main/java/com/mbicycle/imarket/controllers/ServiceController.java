package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.services.securities.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class ServiceController {

    @Autowired
    private SecurityServiceImpl securityService;

    @Autowired
    private ProfileFacade profileFacade;

    @PostMapping(value = "/login")
    public ResponseEntity login() {
        if(securityService.findLoggedUser()!=null)
            return entityWithStatus(302);
        return entityWithStatus(401);       //Doesn't work = it never called (instead calling MyBasicAuthenticationEntryPoint.commence with 401)
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registration(@RequestBody ProfileDTO profileDTO, BindingResult bindingResult) {
        System.out.println("***SOUT***: Registration of login=" + profileDTO.getLogin() + " and password="+profileDTO.getPassword());
        if (!profileFacade.add(profileDTO)){
            System.out.println("***SOUT***: Есть такой юзер");
            return new ResponseEntity(HttpStatus.valueOf(409));
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
