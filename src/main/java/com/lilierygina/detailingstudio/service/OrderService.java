package com.lilierygina.detailingstudio.service;

import com.lilierygina.detailingstudio.entity.Order;

import java.util.List;

public interface OrderService {

  List<Order> findAll();

  Order findById(int theId);

  void save(Order theOrder);

  void deleteById(int theId);
}
