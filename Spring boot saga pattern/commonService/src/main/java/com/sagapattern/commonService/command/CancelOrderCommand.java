package com.sagapattern.commonService.command;

import com.sagapattern.commonService.AppUtil.AppUtil;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CancelOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;

    private String orderStatus = AppUtil.orderStatus.CANCELLED.getValue();
}
