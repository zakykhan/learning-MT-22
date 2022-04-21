package com.sagapattern.commonService.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompleteOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;

    private String orderStatus;


}
