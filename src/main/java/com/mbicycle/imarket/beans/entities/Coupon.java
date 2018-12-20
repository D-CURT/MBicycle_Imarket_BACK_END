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

    private String sum;

    @ManyToOne
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;

    public Coupon() {
    }

    public Coupon(String description, String sum) {
        this.description = description;
        this.sum = sum;
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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", sum='" + sum + '\'' +
                ", profile=" + profile +
                '}';
    }
}
