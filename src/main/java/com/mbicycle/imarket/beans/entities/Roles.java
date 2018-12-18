package com.mbicycle.imarket.beans.entities;

import com.mbicycle.imarket.utils.RoleType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "role_id_seq")
    private int id;

    @Column(name = "role", nullable = false, length = 8)
    private RoleType role;

    @ManyToMany(mappedBy = "roles")
    private List<Users> users;

}
