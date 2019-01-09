package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.Role;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.services.securities.SecurityService;
import com.mbicycle.imarket.utils.converters.Converter;

import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.services.interfaces.ProfileService;
import com.mbicycle.imarket.services.interfaces.UserService;
import com.mbicycle.imarket.utils.enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@SuppressWarnings("ALL")
public class ProfileFacadeImpl implements ProfileFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private Converter<ProfileDTO, Profile> reversedProfileConverter;

    @Autowired
    private Converter<Profile, ProfileDTO> profileConverter;

    @Autowired
    private Converter<UserDTO, User> reverseduserConverter;

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean add(ProfileDTO dto) {
        List<String> defaultRole = new ArrayList<>();
        defaultRole.add(RoleType.CUSTOMER.name());
        dto.setRoles(defaultRole);
        Profile profileToAdd = reversedProfileConverter.convert(dto);
        User userToAdd = new User(profileToAdd.getUser().getLogin(),profileToAdd.getUser().getPassword());
        userToAdd.setRoles(profileToAdd.getUser().getRoles());
        if(!userService.add(userToAdd)) {
            return false;
        }
        profileToAdd.setUser(userToAdd);
        profileService.add(profileToAdd);
        securityService.autoLogin(userToAdd.getLogin(), userToAdd.getPassword());
        return true; //FIXME: Should return true ONLY if transactional adding of both profile and user is succesfull AND user is not already exists in DataBase
    }

    @Override
    public boolean update(ProfileDTO dto) {
        return profileService.update(reversedProfileConverter.convert(dto));
    }

    @Override
    public boolean delete(ProfileDTO dto) {
        return profileService.delete(reversedProfileConverter.convert(dto));
    }

    @Override
    public ProfileDTO get(ProfileDTO dto) {
//        UserDTO userDTO = new UserDTO(dto.getLogin(), dto.getPassword(), dto.getRoles());
//        return profileConverter.convert(profileService.get(reverseduserConverter.convert(userDTO)));
        Profile profileFromdDTO = reversedProfileConverter.convert(dto);
        return profileConverter.convert(profileService.get(profileFromdDTO.getUser()));
    }

    @Override
    public List<ProfileDTO> findByOrderByName() {
        return profileService.findByOrderByName()
                             .stream()
                             .map(profileConverter::convert)
                             .collect(Collectors.toList());
    }

    @Override
    public boolean updateRole(ProfileDTO dto) {

        Profile convert = reversedProfileConverter.convert(dto);
        List<Role> roles2Update = new ArrayList<>();
        for (String role : dto.getRoles()) {  //Assuming default role is already set, or it may produce NullPointerException
            roles2Update.add(RoleType.valueOf(role).getRole());
        }

        return profileService.updateRole(convert,roles2Update);
    }
}
