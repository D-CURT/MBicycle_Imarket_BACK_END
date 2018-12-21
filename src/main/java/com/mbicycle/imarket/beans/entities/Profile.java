package com.mbicycle.imarket.beans.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @SequenceGenerator(name = "profiles_sequence_generator", sequenceName = "profiles_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profiles_sequence_generator")
    private int id;

    private String name;

    private String email;

    private String phone;

    private String address;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<Coupon> coupons;

    @Column(length = 2)
    private String discriminator;

    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Order> orders;

    public Profile() {
    }

    public Profile(String name, String email, String phone, String address, User user, String discriminator) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.user = user;
        this.discriminator = discriminator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
