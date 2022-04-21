package com.sagapattern.commonService.events;

import lombok.Data;

@Data
public class OrderCancelledEvent {

    public String orderId;

    private String orderStatus;
}
