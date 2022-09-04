package com.lilierygina.detailingstudio.service;

import com.lilierygina.detailingstudio.dao.OrderRepository;
import com.lilierygina.detailingstudio.dao.ServicesListRepository;
import com.lilierygina.detailingstudio.entity.Order;
import com.lilierygina.detailingstudio.entity.ServicesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesListServiceImpl implements ServicesListService {

    private final ServicesListRepository servicesListRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ServicesListServiceImpl(ServicesListRepository servicesListRepository,
                                   OrderRepository orderRepository) {
        this.servicesListRepository = servicesListRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public ServicesList findByOrderId(int orderId) {
        Optional<Order> result = orderRepository.findById(orderId);
        ServicesList theServiceList = null;
        if (result.isPresent()) {
            theServiceList = result.get().getServicesList();
        } else {
            throw new RuntimeException("Did not find serviceList id - " + orderId);
        }
        return theServiceList;
    }

    @Override
    public void save(ServicesList theServicesList) {
        servicesListRepository.save(theServicesList);
    }
}
