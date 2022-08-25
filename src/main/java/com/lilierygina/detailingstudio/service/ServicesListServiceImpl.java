package com.lilierygina.detailingstudio.service;

import com.lilierygina.detailingstudio.dao.ServicesListRepository;
import com.lilierygina.detailingstudio.entity.ServicesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesListServiceImpl implements ServicesListService {

    private final ServicesListRepository servicesListRepository;

    @Autowired
    public ServicesListServiceImpl(ServicesListRepository servicesListRepository) {
        this.servicesListRepository = servicesListRepository;
    }
    @Override
    public ServicesList findById(int theId) {
        Optional<ServicesList> result = servicesListRepository.findById(theId);
        ServicesList theServiceList = null;
        if (result.isPresent()) {
            theServiceList = result.get();
        } else {
            throw new RuntimeException("Did not find serviceList id - " + theId);
        }
        return theServiceList;
    }

    @Override
    public void save(ServicesList theServicesList) {
        servicesListRepository.save(theServicesList);
    }
}
