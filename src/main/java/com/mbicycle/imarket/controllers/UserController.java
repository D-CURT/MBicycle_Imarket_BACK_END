package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class UserController {
    private static final String MAPPING = "/users";

    @Autowired
    @SuppressWarnings("ALL")
    private UserFacade userFacade;

    @GetMapping(MAPPING + "/allUsersSortedByLogin")
    public List<UserDTO> getAllUsersSortedByLogin() {
        return userFacade.findByOrderByLogin();
    }

    @GetMapping(value = MAPPING + "/getByLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO getByLogin(@RequestBody UserDTO dto) {
         return userFacade.get(dto);
    }

    @GetMapping(value = MAPPING + "/getByLoginAndPassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO getByLoginAndPassword(@RequestBody UserDTO dto) {
        return userFacade.get(dto);
    }

    @PostMapping(value = MAPPING + "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody UserDTO dto) {

        return entityWithStatus(userFacade.add(dto));
    }

    @PostMapping(value = MAPPING + "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody UserDTO dto) {
        return entityWithStatus(userFacade.delete(dto));
    }
}
