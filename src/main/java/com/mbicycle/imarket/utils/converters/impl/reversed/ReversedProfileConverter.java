package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.services.securities.SecurityService;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.services.interfaces.UserService;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReversedProfileConverter extends AbstractConverter<ProfileDTO, Profile> {

    @Autowired
    @SuppressWarnings("ALL")
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    SecurityService securityService;

    @Override
    public void convert(ProfileDTO source, Profile target) {

        String login = null;
        String password = null;
        if (securityService.findLoggedUser()!=null) {
            login = securityService.findLoggedInUsername();
            password = securityService.findLoggedUser().getPassword();
        }

        if(source==null) {
            User user  = new User(securityService.findLoggedInUsername());
            User userInDb = userRepository.findByLogin(user.getLogin());
            target.setUser(userInDb);
        }
        else {
            target.setName(source.getName());
            target.setEmail(source.getEmail());
            target.setPhone(source.getPhone());
            target.setAddress(source.getAddress());
//        userService.add(new User(login
//                , source.getPassword()
//                , source.getRoles()
//                        .stream()
//                        .map(s -> RoleType.valueOf(s).getRole())
//                        .collect(Collectors.toList())));
            String newLogin = source.getLogin()!=null ? source.getLogin() : login;
            User user = null;
            if(source.getPassword()!=null)
                user = new User(newLogin,bCryptPasswordEncoder.encode(source.getPassword()));
            else
                user = new User(newLogin);
            User userInDb = userRepository.findByLogin(user.getLogin());
            if (userInDb == null) {    // Only when registering new user.
                List<Role> rolesGet = new ArrayList<>();
                if(source.getRoles()==null) {   //User exists, find role in security service
                    for (String role : securityService.getRoles()) {  //Assuming default role is already set, or it may produce NullPointerException
                        rolesGet.add(RoleType.valueOf(role).getRole());
                    }
                }
                else {
                    for (String role : source.getRoles()) {  //Assuming default role is already set, or it may produce NullPointerException
                        rolesGet.add(RoleType.valueOf(role).getRole());
                    }
                }
                user.setRoles(rolesGet);
            } else {
                user.setRoles(userInDb.getRoles());
            }
            target.setUser(user);
        }
    }
}
