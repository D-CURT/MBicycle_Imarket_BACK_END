package com.mbicycle.imarket.beans.entities;

import javax.persistence.*;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @SequenceGenerator(name = "coupons_sequence_generator", sequenceName = "coupons_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupons_sequence_generator")
    private int id;

    private String description;

    private Integer sum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = true)
    private Profile profile;

    public Coupon() {
    }

    public Coupon(String description, Integer sum, Profile profile) {
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
