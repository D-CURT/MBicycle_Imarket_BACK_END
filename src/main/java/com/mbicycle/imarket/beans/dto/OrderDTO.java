package com.mbicycle.imarket.beans.dto;

import com.mbicycle.imarket.beans.entities.Product;
import com.mbicycle.imarket.utils.enums.DeliveryType;
import com.mbicycle.imarket.utils.enums.PaymentType;

import java.util.List;

public class OrderDTO {

    private int id;

    private List<Integer> productsIds;

    private List<Product> products;

    private PaymentType payment;

    private DeliveryType delivery;

    private String dateOpened;

    private String datePaid;

    private String dateReady;

    private String dateClosed;

    private String dateSent;

    private String dateGot;

    public OrderDTO() {
    }

    public OrderDTO(List<Integer> productsIds, PaymentType payment, DeliveryType delivery, String dateOpened, String datePaid, String dateReady, String dateClosed, String dateSent, String dateGot) {
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

    public String getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(String dateOpened) {
        this.dateOpened = dateOpened;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    public String getDateReady() {
        return dateReady;
    }

    public void setDateReady(String dateReady) {
        this.dateReady = dateReady;
    }

    public String getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(String dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public String getDateGot() {
        return dateGot;
    }

    public void setDateGot(String dateGot) {
        this.dateGot = dateGot;
    }

    public List<Integer> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Integer> productsIds) {
        this.productsIds = productsIds;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
