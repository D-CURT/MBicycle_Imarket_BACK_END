package com.mbicycle.imarket.services.interfaces;

import com.mbicycle.imarket.beans.dto.ProfileDTO;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public interface ProfileService {
    boolean add(Profile profile);

    boolean update(Profile profile);

    boolean delete(Profile profile);

    Profile get(User user);

    List<Profile> findByOrderByName();

    List<Profile> getCustomers();
}
