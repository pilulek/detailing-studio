package com.lilierygina.detailingstudio.dao;

import com.lilierygina.detailingstudio.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmoloyeeRepository extends JpaRepository<Order, Integer> {
}
