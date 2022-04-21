package com.sagapattern.orderService.command.api.aggregate;

import com.sagapattern.commonService.command.CancelOrderCommand;
import com.sagapattern.commonService.command.CompleteOrderCommand;
import com.sagapattern.commonService.events.OrderCancelledEvent;
import com.sagapattern.commonService.events.OrderCompletedEvent;
import com.sagapattern.orderService.command.api.command.OrderCreateCommand;
import com.sagapattern.orderService.command.api.events.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.beans.BeanUtils;

public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;

    private String orderStatus;

    public OrderAggregate(){

    }

    @CommandHandler
    public OrderAggregate(OrderCreateCommand orderCreateCommand){

        //Validate the command
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(orderCreateCommand, orderCreatedEvent);
        AggregateLifecycle.apply(orderCreatedEvent);

    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent){
        String addressId = orderCreatedEvent.getAddressId();
        String userId = orderCreatedEvent.getUserId();
        this.orderId = orderCreatedEvent.getOrderId();
        String productId = orderCreatedEvent.getProductId();
        Integer quantity = orderCreatedEvent.getQuantity();
        this.orderStatus = orderCreatedEvent.getOrderStatus();

    }

    @CommandHandler
    public  void handle(CompleteOrderCommand command){

        //Validate the command

        //Publish Order Completed event

        OrderCompletedEvent event = OrderCompletedEvent.builder()
                .orderId(command.getOrderId())
                .orderStatus(command.getOrderStatus())
                .build();

        AggregateLifecycle.apply(event);

    }

    @EventSourcingHandler
    public void on(OrderCompletedEvent event){
        this.orderStatus = event.getOrderStatus();
    }

    @CommandHandler
    public void handle(CancelOrderCommand command){

        OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent();
                BeanUtils.copyProperties(command, orderCancelledEvent);

                AggregateLifecycle.apply(orderCancelledEvent);

    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event){
        this.orderStatus = event.getOrderStatus();

    }
}
