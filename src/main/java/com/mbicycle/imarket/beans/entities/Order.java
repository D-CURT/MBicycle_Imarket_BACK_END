package com.mbicycle.imarket.beans.entities;

import com.mbicycle.imarket.utils.DeliveryType;
import com.mbicycle.imarket.utils.PaymentType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(name = "orders_sequence_generator", sequenceName = "orders_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence_generator")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;

    @Column(length = 5)
    private PaymentType payment;

    @Column(length = 8)
    private DeliveryType delivery;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_opened;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_paid;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_ready;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_closed;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_sent;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_got;

    public Order() {
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

    public Date getDate_opened() {
        return date_opened;
    }

    public void setDate_opened(Date date_opened) {
        this.date_opened = date_opened;
    }

    public Date getDate_paid() {
        return date_paid;
    }

    public void setDate_paid(Date date_paid) {
        this.date_paid = date_paid;
    }

    public Date getDate_ready() {
        return date_ready;
    }

    public void setDate_ready(Date date_ready) {
        this.date_ready = date_ready;
    }

    public Date getDate_closed() {
        return date_closed;
    }

    public void setDate_closed(Date date_closed) {
        this.date_closed = date_closed;
    }

    public Date getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(Date date_sent) {
        this.date_sent = date_sent;
    }

    public Date getDate_got() {
        return date_got;
    }

    public void setDate_got(Date date_got) {
        this.date_got = date_got;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", profiles=" + profile +
                ", payment=" + payment +
                ", delivery=" + delivery +
                ", date_opened=" + date_opened +
                ", date_paid=" + date_paid +
                ", date_ready=" + date_ready +
                ", date_closed=" + date_closed +
                ", date_sent=" + date_sent +
                ", date_gott=" + date_got +
                '}';
    }
}
