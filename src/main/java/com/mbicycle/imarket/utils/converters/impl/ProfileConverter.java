package com.mbicycle.imarket.utils.converters.impl;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.utils.converters.AbstractConverter;
import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.utils.enums.RoleType;

import java.util.stream.Collectors;

public class ProfileConverter extends AbstractConverter<Profile, ProfileDTO> {

    @Override
    public void convert(Profile profile,ProfileDTO profileDTO) {
        profileDTO.setName(profile.getName());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setPhone(profile.getPhone());
        profileDTO.setAddress(profile.getAddress());

    }
}
