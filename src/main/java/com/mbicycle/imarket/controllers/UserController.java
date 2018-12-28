package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
=======
import org.springframework.http.MediaType;
>>>>>>> afc5ef03b0cea2f3f0da9644efaeec8c1c395b1b
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

<<<<<<< HEAD
    @GetMapping("/users/add")
    public ResponseEntity addUser(UserDTO dto) {
        return userFacade.add(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/delete")
    public ResponseEntity deleteUser(UserDTO dto) {
        return userFacade.delete(dto) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
=======
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
>>>>>>> afc5ef03b0cea2f3f0da9644efaeec8c1c395b1b
    }
}
