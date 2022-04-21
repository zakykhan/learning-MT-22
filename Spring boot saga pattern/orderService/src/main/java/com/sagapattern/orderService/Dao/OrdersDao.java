package com.sagapattern.orderService.Dao;

import com.sagapattern.orderService.command.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDao extends JpaRepository<Order, Long> {
}
