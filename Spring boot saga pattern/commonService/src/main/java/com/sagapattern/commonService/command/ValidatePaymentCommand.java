package com.sagapattern.commonService.command;

import com.sagapattern.commonService.model.CardDetails;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ValidatePaymentCommand {

    @TargetAggregateIdentifier
    private String paymentId;

    private String orderId;

    private CardDetails cardDetails;
}
