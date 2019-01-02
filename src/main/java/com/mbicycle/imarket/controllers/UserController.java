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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithContent;
import static com.mbicycle.imarket.utils.ResponseEntityBuilder.entityWithStatus;

@RestController
@SuppressWarnings("ALL")
public class UserController {
    private static final String MAPPING = "/users";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

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
        System.out.println("***SOUT***: Registration of login=" + profileDTO.getLogin() + " and password="+profileDTO.getPassword());
        if (!profileFacade.add(profileDTO)){
            System.out.println("***SOUT***: Есть такой юзер");
            return new ResponseEntity(HttpStatus.valueOf(409));
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = MAPPING + "/get", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
