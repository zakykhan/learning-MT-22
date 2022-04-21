package com.sagapattern.commonService.events;

import lombok.Data;

@Data
public class PaymentCancelledEvent {

    private String orderId;

    private String paymentId;

    private String paymentStatus;
}
