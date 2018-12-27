package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;

import java.util.List;

public interface ProfileService {
    void addProfile(Profile profile);

    void delete(User user);

    Profile findByUser(User user);

    List<Profile> findByOrderByName();


}
