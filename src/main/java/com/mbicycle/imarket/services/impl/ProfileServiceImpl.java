package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.ProfileRepository;
import com.mbicycle.imarket.services.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProfileServiceImpl implements ProfileService {

    @Autowired
    @SuppressWarnings("ALL")
    private ProfileRepository repository;

    @Override
    public boolean add(Profile profile){
        User user;
        if ((user = profile.getUser()) == null) {
            return false;
        }
        if (repository.findByUser(user) == null) {
            repository.save(profile);
        }
        return get(user) != null;
    }

    @Override
    public boolean delete(Profile profile){
        User user = profile.getUser();
        if (get(user) != null) {
            repository.delete(profile);
        }
        return get(user) == null;
    }

    @Override
    public Profile get(User user){
        return repository.findByUser(user);
    }

    @Override
    public List<Profile> findByOrderByName(){
        return repository.findByOrderByNameAsc();
    }
}
