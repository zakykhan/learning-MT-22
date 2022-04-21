package com.sagapattern.orderService.command.api.entity;

import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;

    private String productId;

    private String userId;

    private String addressId;

    private Integer quantity;

    private String orderStatus;


}
