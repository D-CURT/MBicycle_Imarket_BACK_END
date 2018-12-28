package com.mbicycle.imarket.dto;

public class CouponDTO {
    private int id;
    private String description;
    private Integer sum;
    private String profile;

    public CouponDTO() {
    }

    public CouponDTO(String description, Integer sum, String profile) {
        setDescription(description);
        setSum(sum);
        setProfile(profile);
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
