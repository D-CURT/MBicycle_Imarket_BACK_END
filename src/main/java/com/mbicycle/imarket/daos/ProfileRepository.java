package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
