package com.lilierygina.detailingstudio.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Table(name = "services_list")
public class ServicesList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "service_name1")
    private String serviceName1;

    @Column(name = "service_price1")
    private Integer servicePrice1;

    @Column(name = "service_name2")
    private String serviceName2;

    @Column(name = "service_price2")
    private Integer servicePrice2;

    @Column(name = "service_name3")
    private String serviceName3;

    @Column(name = "service_price3")
    private Integer servicePrice3;
    @Formula("service_price1 + service_price2 + service_price3")
    @Column(name = "total_price")
    private Integer totalPrice;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "serviceId")
    private Order orderId;

    public ServicesList() {
    }

    public ServicesList(ServicesList servicesList) {
        this.serviceName1 = servicesList.serviceName1;
        this.servicePrice1 = servicesList.servicePrice1;
        this.serviceName2 = servicesList.serviceName2;
        this.servicePrice2 = servicesList.servicePrice2;
        this.serviceName3 = servicesList.serviceName3;
        this.servicePrice3 = servicesList.servicePrice3;
        this.totalPrice = servicesList.totalPrice;
        this.orderId = servicesList.orderId;
    }

    public ServicesList(String serviceName1,
                        Integer servicePrice1,
                        String serviceName2,
                        Integer servicePrice2,
                        String serviceName3,
                        Integer servicePrice3,
                        Integer totalPrice,
                        Order orderId) {
        this.serviceName1 = serviceName1;
        this.servicePrice1 = servicePrice1;
        this.serviceName2 = serviceName2;
        this.servicePrice2 = servicePrice2;
        this.serviceName3 = serviceName3;
        this.servicePrice3 = servicePrice3;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName1() {
        return serviceName1;
    }

    public void setServiceName1(String serviceName1) {
        this.serviceName1 = serviceName1;
    }

    public Integer getServicePrice1() {
        return servicePrice1;
    }

    public void setServicePrice1(Integer servicePrice1) {
        this.servicePrice1 = servicePrice1;
    }

    public String getServiceName2() {
        return serviceName2;
    }

    public void setServiceName2(String serviceName2) {
        this.serviceName2 = serviceName2;
    }

    public Integer getServicePrice2() {
        return servicePrice2;
    }

    public void setServicePrice2(Integer servicePrice2) {
        this.servicePrice2 = servicePrice2;
    }

    public String getServiceName3() {
        return serviceName3;
    }

    public void setServiceName3(String serviceName3) {
        this.serviceName3 = serviceName3;
    }

    public Integer getServicePrice3() {
        return servicePrice3;
    }

    public void setServicePrice3(Integer servicePrice3) {
        this.servicePrice3 = servicePrice3;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "ServicesList{" +
            "id=" + id +
            ", serviceName1='" + serviceName1 + '\'' +
            ", servicePrice1=" + servicePrice1 +
            ", serviceName2='" + serviceName2 + '\'' +
            ", servicePrice2=" + servicePrice2 +
            ", serviceName3='" + serviceName3 + '\'' +
            ", servicePrice3=" + servicePrice3 +
            ", totalPrice=" + totalPrice +
            ", orderId=" + orderId +
            '}';
    }
}
