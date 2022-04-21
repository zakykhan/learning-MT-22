package com.sagapattern.orderService.command.api.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class OrderCreateCommand {

    @TargetAggregateIdentifier
    private String orderId;

    private String productId;

    private String userId;

    private String addressId;

    private Integer quantity;

    private String orderStatus;
}
