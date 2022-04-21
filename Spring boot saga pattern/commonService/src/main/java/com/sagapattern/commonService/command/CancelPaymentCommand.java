package com.sagapattern.commonService.command;


import com.sagapattern.commonService.AppUtil.AppUtil;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CancelPaymentCommand {

    @TargetAggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus = AppUtil.orderStatus.CANCELLED.getValue();
}