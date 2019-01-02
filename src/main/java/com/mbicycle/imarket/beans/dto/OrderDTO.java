package com.mbicycle.imarket.beans.dto;

import com.mbicycle.imarket.utils.enums.DeliveryType;
import com.mbicycle.imarket.utils.enums.PaymentType;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private int id;

    private String userLogin;

    private List<Integer> productsIds;

    private PaymentType payment;

    private DeliveryType delivery;

    private Date dateOpened;

    private Date datePaid;

    private Date dateReady;

    private Date dateClosed;

    private Date dateSent;

    private Date dateGot;

    public OrderDTO() {
    }

    public OrderDTO(String userLogin,List<Integer> productsIds, PaymentType payment, DeliveryType delivery, Date dateOpened, Date datePaid, Date dateReady, Date dateClosed, Date dateSent, Date dateGot) {
        setUserLogin(userLogin);
        setProductsIds(productsIds);
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public List<Integer> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Integer> productsIds) {
        this.productsIds = productsIds;
    }
}
