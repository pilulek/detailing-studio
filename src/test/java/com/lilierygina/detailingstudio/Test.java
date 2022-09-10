package com.lilierygina.detailingstudio;

import com.lilierygina.detailingstudio.entity.Order;
import com.lilierygina.detailingstudio.service.OrderService;

import java.util.List;

public class Test {

    OrderService orderService;
    @org.junit.jupiter.api.Test
    void CarTest() {

        List<Order> orders =  orderService.findAll();
        System.out.println(orders);
    }
}
