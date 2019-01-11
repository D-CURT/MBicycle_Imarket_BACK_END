package com.mbicycle.imarket.daos;

import com.mbicycle.imarket.beans.entities.Order;
import com.mbicycle.imarket.beans.entities.Profile;
import com.mbicycle.imarket.beans.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Profile findByUser(User user);

    List<Profile> findByOrderByNameAsc();

    @Query(value = "SELECT profiles.* FROM profiles, users, user_roles, roles " +
            "WHERE roles = 0 AND user_roles.id_user = user.id " +
            "AND user.id = profiles.id_user ", nativeQuery = true)
    List<Profile> getCustomers();
}
