package com.mbicycle.imarket.services.impl;

import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.Role;
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
            Profile profileInDB = repository.findByUser(user);
            profile.setId(profileInDB.getId());
            profile.setUser(profileInDB.getUser());     //Assuming that User cannot be changed in the profile
            profile.setAddress(profile.getAddress() != null ? profile.getAddress() : profileInDB.getAddress());
            profile.setEmail(profile.getEmail() != null ? profile.getEmail() : profileInDB.getEmail());
            profile.setPhone(profile.getPhone() != null ? profile.getPhone() : profileInDB.getPhone());
            profile.setName(profile.getName() != null ? profile.getName() : profileInDB.getName());
            profile.setDiscriminator(profile.getDiscriminator() != null ? profile.getDiscriminator() : profileInDB.getDiscriminator());
            profile.setOrders(profile.getOrders() != null ? profile.getOrders() : profileInDB.getOrders());
            profile.setCoupons(profile.getCoupons() != null ? profile.getCoupons() : profileInDB.getCoupons());
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
    public boolean updateRole(Profile convert, List<Role> roles2Update) {
        User profileUser = convert.getUser();
        profileUser.setRoles(roles2Update);
        convert.setUser(profileUser);
        repository.save(convert);
        return true;
    }

    @Override
    public List<Profile> findByOrderByName(){
        return repository.findByOrderByNameAsc();
    }

}
