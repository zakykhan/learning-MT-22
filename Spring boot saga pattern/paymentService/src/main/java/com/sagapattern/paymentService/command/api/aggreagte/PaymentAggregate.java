package com.sagapattern.paymentService.command.api.aggreagte;

import com.sagapattern.commonService.command.CancelPaymentCommand;
import com.sagapattern.commonService.command.ValidatePaymentCommand;
import com.sagapattern.commonService.events.PaymentCancelledEvent;
import com.sagapattern.commonService.events.PaymentProcessedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Slf4j
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;

    private String orderId;

    private String paymentStatus;

    public PaymentAggregate(){

    }

    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand){

        //Here validate the payment details

        //Also publish the payment processed event

        log.info("Executing validatePaymentCommand for orderId: {} and paymentId: {}"
                , validatePaymentCommand.getOrderId(), validatePaymentCommand.getPaymentId());

        PaymentProcessedEvent paymentProcessedEvent =
                new PaymentProcessedEvent(validatePaymentCommand.getOrderId(), validatePaymentCommand.getPaymentId());

        AggregateLifecycle.apply(paymentProcessedEvent);

        log.info("PaymentProcessedEvent Applied..!");

    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent  event){
        this.paymentId = event.getPaymentId();
        this.orderId = event.getOrderId();
    }

    @CommandHandler
    public void handle(CancelPaymentCommand command){

        PaymentCancelledEvent paymentCancelledEvent = new PaymentCancelledEvent();
        BeanUtils.copyProperties(command, paymentCancelledEvent);

        AggregateLifecycle.apply(paymentCancelledEvent);

    }

    @EventSourcingHandler
    public void on(PaymentCancelledEvent event){
        this.paymentStatus = event.getPaymentStatus();
    }
}
