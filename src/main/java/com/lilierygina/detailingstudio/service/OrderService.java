package com.lilierygina.detailingstudio.service;

import com.lilierygina.detailingstudio.entity.Order;

import java.util.List;

public interface OrderService {

  public List<Order> findAll();

  public Order findById(int theId);

  public void save(Order theOrder);

  public void deleteById(int theId);
}
