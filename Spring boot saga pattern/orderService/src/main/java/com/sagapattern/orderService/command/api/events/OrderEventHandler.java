package com.sagapattern.orderService.command.api.events;

import com.sagapattern.commonService.events.OrderCancelledEvent;
import com.sagapattern.commonService.events.OrderCompletedEvent;
import com.sagapattern.orderService.Dao.OrdersDao;
import com.sagapattern.orderService.command.api.entity.Order;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderEventHandler {

    private OrdersDao ordersDao;

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent){

        Order order = new Order();
        BeanUtils.copyProperties(orderCreatedEvent, order);
        ordersDao.save(order);
    }

    @EventHandler
    public void on(OrderCompletedEvent event){

        Optional<Order> orderObj = ordersDao.findById(Long.parseLong(event.getOrderId()));
        Order order = orderObj.orElse(null);

        assert order != null;
        order.setOrderStatus(event.getOrderStatus());

        ordersDao.save(order);
    }

    @EventHandler
    public void on(OrderCancelledEvent event){
        Optional<Order> orderObj = ordersDao.findById(Long.parseLong(event.getOrderId()));
        Order order = orderObj.orElse(null);

        assert order != null;
        order.setOrderStatus(event.getOrderStatus());

        ordersDao.save(order);
    }
}
