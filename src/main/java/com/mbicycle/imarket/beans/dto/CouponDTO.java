package com.mbicycle.imarket.beans.dto;

import java.util.HashMap;

public class CouponDTO {
    private int id;
    private String description;
    private Integer sum;
    private String login;

    public CouponDTO() {
    }

    public CouponDTO(String description, Integer sum, String login) {
        setDescription(description);
        setSum(sum);
        setLogin(login);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
