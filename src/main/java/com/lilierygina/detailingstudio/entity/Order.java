package com.lilierygina.detailingstudio.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "detailing_orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "car_owner")
    private String owner;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "car_color")
    private String carColor;

    @Column(name = "car_interior")
    private String carInterior;


    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "bonus")
    private String bonus;

    @Column(name = "previous_order")
    private String previousOrder;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private ServicesList serviceId;

    public Order() {
    }

    public Order(Date orderDate, String owner,
                 String phoneNumber, String carModel,
                 String carColor, String carInterior,
                 BigDecimal price, String bonus,
                 ServicesList serviceId) {
        this.orderDate = orderDate;
        this.owner = owner;
        this.phoneNumber = phoneNumber;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carInterior = carInterior;
        this.price = price;
        this.bonus = bonus;
        this.previousOrder = previousOrder;
        this.serviceId = serviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarInterior() {
        return carInterior;
    }

    public void setCarInterior(String carInterior) {
        this.carInterior = carInterior;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getPreviousOrder() {
        return previousOrder;
    }

    public void setPreviousOrder(String previousOrder) {
        this.previousOrder = previousOrder;
    }

    public ServicesList getServicesList() {
        return serviceId;
    }

    public void setServicesList(ServicesList servicesList) {
        this.serviceId = servicesList;
    }
}
