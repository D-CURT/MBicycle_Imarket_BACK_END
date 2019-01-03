package com.mbicycle.imarket.utils.converters.impl.reversed;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.UserRepository;
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

    @Override
    public void convert(ProfileDTO source, Profile target) {
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setPhone(source.getPhone());
        target.setAddress(source.getAddress());
        String login = source.getLogin();
//        userService.add(new User(login
//                , source.getPassword()
//                , source.getRoles()
//                        .stream()
//                        .map(s -> RoleType.valueOf(s).getRole())
//                        .collect(Collectors.toList())));
        User user  = new User(source.getLogin(),encoder.encode(source.getPassword()));
        User userInDb = userRepository.findByLoginAndPassword(user.getLogin(),user.getPassword());
        if(userInDb==null) {    // Only when registering new user.
            List<Role> rolesGet = new ArrayList<>();
            for (String role: source.getRoles()) {  //Assuming default role is already set, or it may produce NullPointerException
                rolesGet.add(RoleType.valueOf(role).getRole());
            }
            user.setRoles(rolesGet);
        }
        else {
            user.setRoles(userInDb.getRoles());
        }
        target.setUser(user);
    }
}
