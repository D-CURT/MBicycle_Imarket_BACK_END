package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import com.mbicycle.imarket.daos.ProfileRepository;
import com.mbicycle.imarket.daos.UserRepository;
import com.mbicycle.imarket.services.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@SuppressWarnings("ALL")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
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
        else {
            return false;
        }
        return get(user) != null;
    }

    @Override
    public boolean update(Profile profile){
        User user;
        if ((user = profile.getUser()) == null) {
            return false;
        }
        if ( (user = userRepository.findByLogin(user.getLogin())) != null) {
            Profile profileIdDB = repository.findByUser(user);
            profile.setId(profileIdDB.getId());
            profile.setUser(profileIdDB.getUser());     //Assuming that User cannot be changed in the profile
            profile.setAddress(profile.getAddress() != null ? profile.getAddress() : profileIdDB.getAddress());
            profile.setEmail(profile.getEmail() != null ? profile.getEmail() : profileIdDB.getEmail());
            profile.setPhone(profile.getPhone() != null ? profile.getPhone() : profileIdDB.getPhone());
            profile.setName(profile.getName() != null ? profile.getName() : profileIdDB.getName());
            profile.setDiscriminator(profile.getDiscriminator() != null ? profile.getDiscriminator() : profileIdDB.getDiscriminator());
            profile.setOrders(profile.getOrders() != null ? profile.getOrders() : profileIdDB.getOrders());
            profile.setCoupons(profile.getCoupons() != null ? profile.getCoupons() : profileIdDB.getCoupons());
            repository.save(profile);
        }
        else {
            return false;
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
        if ((user = userRepository.findByLogin(user.getLogin())) != null) {
            return repository.findByUser(user);
        }
        return null;
    }

    @Override
    public List<Profile> findByOrderByName(){
        return repository.findByOrderByNameAsc();
    }

}
