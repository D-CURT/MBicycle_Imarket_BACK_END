package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {
    boolean addProfile(Profile profile);

    void delete(User user);

    Profile findByUser(User user);

    List<Profile> findByOrderByName();


}
