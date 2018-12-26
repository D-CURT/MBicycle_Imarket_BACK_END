package com.mbicycle.imarket.beans.entities;

import com.mbicycle.imarket.utils.enums.DeliveryType;
import com.mbicycle.imarket.utils.enums.PaymentType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(name = "orders_sequence_generator", sequenceName = "orders_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence_generator")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;

    @OneToMany(mappedBy = "order"
            , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts;

    @Column(length = 5)
    private PaymentType payment;

    @Column(length = 8)
    private DeliveryType delivery;

    @Column(name = "date_opened")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOpened;

    @Column(name = "date_paid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaid;

    @Column(name = "date_ready")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReady;

    @Column(name = "date_closed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClosed;

    @Column(name = "date_sent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSent;

    @Column(name = "date_got")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateGot;

    public Order() {
    }

    public Order(Profile profile, PaymentType payment, DeliveryType delivery, Date dateOpened, Date datePaid, Date dateReady, Date dateClosed, Date dateSent, Date dateGot) {
        setProfile(profile);
        setPayment(payment);
        setDelivery(delivery);
        setDateOpened(dateOpened);
        setDatePaid(datePaid);
        setDateReady(dateReady);
        setDateClosed(dateClosed);
        setDateSent(dateSent);
        setDateGot(dateGot);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public final void setProfile(Profile profile) {
        this.profile = profile;
    }

    public PaymentType getPayment() {
        return payment;
    }

    public void setPayment(PaymentType payment) {
        this.payment = payment;
    }

    public DeliveryType getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryType delivery) {
        this.delivery = delivery;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public Date getDateReady() {
        return dateReady;
    }

    public void setDateReady(Date dateReady) {
        this.dateReady = dateReady;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Date getDateGot() {
        return dateGot;
    }

    public void setDateGot(Date dateGot) {
        this.dateGot = dateGot;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
