package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.ProfileRepository;
import com.mbicycle.imarket.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository repository;

    public void addProfile(Profile profile){
        repository.save(profile);
    }

    public void delete(User user){
        repository.deleteByUser(user);
    }
    public Profile findByUser(User user){
        return repository.findByUser(user);
    }

    public List<Profile> findByOrderByName(){
        return repository.findByOrderByNameAsc();
    }
}
