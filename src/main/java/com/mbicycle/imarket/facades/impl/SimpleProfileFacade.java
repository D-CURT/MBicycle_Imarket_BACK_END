package com.mbicycle.imarket.facades.impl;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.converters.Converter;
import com.mbicycle.imarket.dto.ProfileDTO;
import com.mbicycle.imarket.converters.ProfileConverter;
import com.mbicycle.imarket.facades.interfaces.ProfileFacade;
import com.mbicycle.imarket.services.ProfileService;
import com.mbicycle.imarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

<<<<<<< HEAD:src/main/java/com/mbicycle/imarket/facades/ProfileFacade.java
@Component
public interface ProfileFacade {
=======
public class SimpleProfileFacade implements ProfileFacade {
>>>>>>> bef5eb2a18dbf444800576f39546c847c55d41da:src/main/java/com/mbicycle/imarket/facades/impl/SimpleProfileFacade.java

<<<<<<< HEAD
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ProfileService profileService;
//
//    public boolean push(ProfileDTO dto) {
//
//        Converter<ProfileDTO, Profile> converter = new ProfileConverter();
//        Profile profile = new Profile();
//        converter.convert(dto, profile);
//
//        User user = profile.getUser();
//        if (userService.addUser(user)) {
//            user = userService.getUser(user.getLogin(), user.getPassword());
//            profile.setUser(user);
//            profileService.addProfile(profile);
//            return true;
//        }
//        return false;
//    }
=======
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Override
    public boolean addProfile(ProfileDTO dto) {

        Converter<ProfileDTO, Profile> converter = new ProfileConverter();
        Profile profile = new Profile();
        converter.convert(dto, profile);

        User user = profile.getUser();
        //if (userService.addUser(user)) {
            user = userService.getUser(user.getLogin(), user.getPassword());
            profile.setUser(user);
            profileService.addProfile(profile);
            return true;
        //}
        //return false;
    }
>>>>>>> dfcfb1c4c024bed814067ec8f3833d24d0063092
}
