package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.RoleRepository;
import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import com.mbicycle.imarket.services.interfaces.UserService;
import com.mbicycle.imarket.services.securities.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class UserController {
    private static final String MAPPING = "/users";

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ProfileFacade profileFacade;

    @GetMapping(MAPPING + "/allUsersSortedByLogin")
    public List<UserDTO> getAllUsersSortedByLogin() {
        return userFacade.findByOrderByLogin();
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registration(@RequestBody ProfileDTO profileDTO, BindingResult bindingResult) {

        System.out.println("\nregistration");
        System.out.println(profileDTO.getLogin());
        System.out.println(profileDTO.getPassword());

        User user = new User(profileDTO.getLogin(), profileDTO.getLogin());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.getOne(29));//как?
        user.setRoles(roles);
        if (!profileFacade.add(profileDTO)){
            System.out.println("\nHAS ERROR");

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }


        securityService.autoLogin(user.getLogin(), user.getPassword());

        return new ResponseEntity(HttpStatus.OK);
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
