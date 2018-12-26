package com.mbicycle.imarket.facades;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.utils.converters.Converter;
import com.mbicycle.imarket.utils.converters.ProfileConverter;
import com.mbicycle.imarket.utils.converters.reversed.ReverseProfileConverter;

public class ProfileFacade implements Facade<ProfileDTO, Profile> {
    @Override
    public Profile push(ProfileDTO dto, String identifier) {
        Converter<ProfileDTO, Profile> converter = new ProfileConverter();
        return converter.convert(dto);
    }

    @Override
    public ProfileDTO pull(Profile profile) {
        Converter<Profile, ProfileDTO> converter = new ReverseProfileConverter();
        return converter.convert(profile);
    }
}
