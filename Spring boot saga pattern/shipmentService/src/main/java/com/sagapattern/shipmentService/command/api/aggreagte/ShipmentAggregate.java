package com.sagapattern.shipmentService.command.api.aggreagte;

import com.sagapattern.commonService.AppUtil.AppUtil;
import com.sagapattern.commonService.command.ShipOrderCommand;
import com.sagapattern.commonService.events.OrderShippedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ShipmentAggregate {

    public ShipmentAggregate(){

    }

    @CommandHandler
    public ShipmentAggregate(ShipOrderCommand command){

        //Validate this command

        //Then Publish the order shipped event

        OrderShippedEvent orderShippedEvent = OrderShippedEvent.builder()
                .shipmentId(command.getShipmentId())
                .orderId(command.getOrderId())
                .shipmentStatus(AppUtil.shipmentStatus.COMPLETED.getValue())
                .build();

        AggregateLifecycle.apply(orderShippedEvent);
    }

    @EventSourcingHandler
    public void on(OrderShippedEvent event){
        String orderId = event.getOrderId();
        String shipmentId = event.getShipmentId();
        String shipmentStatus = event.getShipmentStatus();
    }
}
