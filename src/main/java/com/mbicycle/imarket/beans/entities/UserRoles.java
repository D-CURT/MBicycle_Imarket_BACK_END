package com.mbicycle.imarket.beans.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRoles {

    @Id
    @SequenceGenerator(name = "user_roles_sequence_generator", sequenceName = "user_roles_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_roles_sequence_generator")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role")
    private Role role;

    public UserRoles() {
    }

    public UserRoles(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
