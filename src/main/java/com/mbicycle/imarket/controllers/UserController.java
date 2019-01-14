package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.daos.RoleRepository;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.beans.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import com.mbicycle.imarket.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;
import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
@RequestMapping("/users")
@SuppressWarnings("ALL")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ProfileFacade profileFacade;

    @GetMapping("/allUsersSortedByLogin")
    public ResponseEntity<List<UserDTO>> getAllUsersSortedByLogin() {
        return entityWithContent(userFacade.findByOrderByLogin());
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDTO> getByLogin(@RequestBody UserDTO dto) {
         return entityWithContent(userFacade.get(dto));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity add(@RequestBody UserDTO dto) {
        return entityWithStatus(userFacade.add(dto));
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@RequestBody UserDTO dto) {
        return entityWithStatus(userFacade.delete(dto));
    }
}
