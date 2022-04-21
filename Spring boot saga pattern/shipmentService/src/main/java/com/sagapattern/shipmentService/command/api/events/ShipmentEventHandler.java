package com.sagapattern.shipmentService.command.api.events;

import com.sagapattern.commonService.events.OrderShippedEvent;
import com.sagapattern.shipmentService.command.api.entity.Shipment;
import com.sagapattern.shipmentService.dao.ShipmentDao;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ShipmentEventHandler {

    private ShipmentDao shipmentDao;

    public ShipmentEventHandler(ShipmentDao shipmentDao) {
        this.shipmentDao = shipmentDao;
    }

    @EventHandler
    public void on(OrderShippedEvent event){

        Shipment shipment = Shipment.builder()
                .shipmentId(event.getShipmentId())
                .orderId(event.getOrderId())
                .shipmentStatus(event.getShipmentStatus())
                .build();

        shipmentDao.save(shipment);
    }



}
