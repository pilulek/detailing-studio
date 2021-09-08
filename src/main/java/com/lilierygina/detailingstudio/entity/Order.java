package com.lilierygina.detailingstudio.entity;


import javax.persistence.*;

@Entity
@Table(name="orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="номер заказа")
  private int id;

  @Column(name="дата заказа")
  private String orderDate;

  @Column(name="ФИО")
  private String owner;

  @Column(name="Телефон")
  private String phoneNumber;

  @Column(name="Транспортное средство")
  private String carModel;

  @Column(name="Цвет авто")
  private String carColor;

  @Column(name="Материал и цвет салона")
  private String carInterior;

  @Column(name="Услуги")
  private String carService;

  @Column(name="Цена")
  private String price;

  @Column(name="Бонусы")
  private String bonus;

  @Column(name="Предыдущие заказы")
  private String previousOrder;

  public Order() {
  }

  public Order(String orderDate, String owner, String phoneNumber, String carModel, String carColor, String carInterior, String carService, String price, String bonus, String previousOrder) {
    this.orderDate = orderDate;
    this.owner = owner;
    this.phoneNumber = phoneNumber;
    this.carModel = carModel;
    this.carColor = carColor;
    this.carInterior = carInterior;
    this.carService = carService;
    this.price = price;
    this.bonus = bonus;
    this.previousOrder = previousOrder;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(String orderDate) {
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

  public String getCarService() {
    return carService;
  }

  public void setCarService(String carService) {
    this.carService = carService;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
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
}
