package com.mbicycle.imarket.beans.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @SequenceGenerator(name = "coupons_sequence_generator", sequenceName = "coupons_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupons_sequence_generator")
    private int id;

    private String description;

    private Integer sum;

    @JsonIgnore
    @ManyToMany(mappedBy = "coupons", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Profile> profiles;

    public Coupon() {
    }

    public Coupon(String description, Integer sum, List<Profile> profiles) {
        setDescription(description);
        setSum(sum);
        setProfiles(profiles);
    }

    public Coupon(String description, Integer sum) {
        setDescription(description);
        setSum(sum);
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

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

}
