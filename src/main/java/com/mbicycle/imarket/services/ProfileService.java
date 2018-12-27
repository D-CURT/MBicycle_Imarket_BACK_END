package com.mbicycle.imarket.services;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
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
