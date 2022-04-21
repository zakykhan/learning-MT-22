package com.sagapattern.shipmentService.command.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "shipment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shipment {

    @Id
    private String shipmentId;

    private String orderId;

    private String shipmentStatus;

}
