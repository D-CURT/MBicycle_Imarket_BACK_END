package com.mbicycle.imarket.beans.entities;

import com.mbicycle.imarket.utils.RoleType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @SequenceGenerator(name = "roles_sequence_generator", sequenceName = "roles_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_sequence_generator")
    private int id;

    @Column(name = "role", nullable = false, unique = true, length = 8)
    private RoleType role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(RoleType role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }
}