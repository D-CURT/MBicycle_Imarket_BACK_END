package com.mbicycle.imarket.utils.converters;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.dto.ProfileDTO;

public class ProfileConverter implements Converter<ProfileDTO, Profile> {
    @Override
    public Profile convert(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setName(profileDTO.getName());
        profile.setEmail(profileDTO.getEmail());
        profile.setPhone(profileDTO.getPhone());
        profile.setAddress(profileDTO.getAddress());
        return profile;
    }
}
