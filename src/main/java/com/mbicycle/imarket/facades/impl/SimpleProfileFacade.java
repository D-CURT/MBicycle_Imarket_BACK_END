package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.converters.Converter;

import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.dto.UserDTO;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.services.ProfileService;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class SimpleProfileFacade implements ProfileFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private Converter<ProfileDTO, Profile> reversedProfileConverter;

    @Autowired
    private Converter<Profile, ProfileDTO> profileConverter;

    @Autowired
    private Converter<UserDTO, User> userConverter;



    @Override
    public boolean addProfile(ProfileDTO dto) {
        return profileService.addProfile(reversedProfileConverter.convert(dto));
    }

    @Override
    public void delete(UserDTO userDTO) {
        profileService.delete(userConverter.convert(userDTO));
    }

    @Override
    public ProfileDTO findByUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<ProfileDTO> findByOrderByName() {
        List<ProfileDTO> profileDTOList = new ArrayList<>();
        for (Profile profile : profileService.findByOrderByName()) {
            ProfileDTO profileDTO = profileConverter.convert(profile);
            profileDTOList.add(profileDTO);
        }
        return profileDTOList;
    }

}
