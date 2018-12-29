package com.mbicycle.imarket.controllers;

import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.RoleRepository;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.beans.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.UserFacade;
import com.mbicycle.imarket.services.interfaces.UserService;
import com.mbicycle.imarket.services.securities.SecurityService;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;
import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
public class UserController {
    private static final String MAPPING = "/users";

    @Autowired
    @SuppressWarnings("ALL")
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ProfileFacade profileFacade;

    @GetMapping(MAPPING + "/allUsersSortedByLogin")
    public ResponseEntity<List<UserDTO>> getAllUsersSortedByLogin() {
        return entityWithContent(userFacade.findByOrderByLogin());
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registration(@RequestBody ProfileDTO profileDTO, BindingResult bindingResult) {

        System.out.println("\nregistration");
        System.out.println(profileDTO.getLogin());
        System.out.println(profileDTO.getPassword());

        User user = new User(profileDTO.getLogin(), profileDTO.getPassword());

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        Role role = new Role(RoleType.CUSTOMER);
        roles.add(role);
        user.setRoles(roles);
        if (!profileFacade.add(profileDTO)){
            System.out.println("\nЕсть такой юзер\n");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        securityService.autoLogin(user.getLogin(), user.getPassword());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = MAPPING + "/getByLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDTO> getByLogin(@RequestBody UserDTO dto) {
         return entityWithContent(userFacade.get(dto));
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
