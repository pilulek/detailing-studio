package com.lilierygina.detailingstudio.service;

import com.lilierygina.detailingstudio.entity.ServicesList;

public interface ServicesListService {
    ServicesList findByOrderId(int theId);

    void save(ServicesList theServicesList);
}
