package com.mbicycle.imarket.utils.converters.reversed;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.utils.converters.Converter;

public class ReverseProfileConverter implements Converter<Profile, ProfileDTO> {
    @Override
    public ProfileDTO convert(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(profile.getName());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setPhone(profile.getPhone());
        profileDTO.setAddress(profile.getAddress());
        return profileDTO;
    }
}
