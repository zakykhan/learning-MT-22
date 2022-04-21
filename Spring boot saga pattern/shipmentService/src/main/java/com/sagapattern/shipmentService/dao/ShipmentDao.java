package com.sagapattern.shipmentService.dao;

import com.sagapattern.shipmentService.command.api.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentDao extends JpaRepository<Shipment, String> {
}
