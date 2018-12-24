package com.mbicycle.imarket.beans.entities;

import com.mbicycle.imarket.utils.enums.RoleType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @SequenceGenerator(name = "roles_sequence_generator", sequenceName = "roles_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_sequence_generator")
    private int id;

    @Column(length = 8)
    private RoleType role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private List<User> users;

    public Role() {
    }

    public Role(RoleType role) {
        setRole(role);
    }

    public int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public RoleType getRole() {
        return role;
    }

    public final void setRole(RoleType role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

}