package com.mbicycle.imarket.dto;

import com.mbicycle.imarket.beans.entities.User;

import java.util.List;
import java.util.Objects;

public class UserDTO {

    private int id;

    private String login;

    private String password;

    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String login, String password, List<String> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        User user = (User) o;
        return Objects.equals(login, user.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
