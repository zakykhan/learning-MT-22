package com.sagapattern.orderService.command.api.saga;

import com.sagapattern.commonService.AppUtil.AppUtil;
import com.sagapattern.commonService.command.*;
import com.sagapattern.commonService.events.*;
import com.sagapattern.commonService.model.User;
import com.sagapattern.commonService.queries.GetUserPaymentDetailsQuery;
import com.sagapattern.orderService.command.api.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class orderProcessingSaga {

    private CommandGateway commandGateway;

    private QueryGateway queryGateway;

    @Autowired
    public orderProcessingSaga(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreatedEvent orderCreatedEvent) {

        log.info("orderCreateEvent in saga for order Id : {}", orderCreatedEvent.getOrderId());

        GetUserPaymentDetailsQuery getUserPaymentDetailsQuery = new GetUserPaymentDetailsQuery(orderCreatedEvent.getUserId());

        User user = null;

        try {
            user = queryGateway.query(getUserPaymentDetailsQuery, ResponseTypes.instanceOf(User.class)).join();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());

            //Start  the command again for compensating if fails
            cancelOrderCommand(orderCreatedEvent.getOrderId());
        }


        assert user != null;
        ValidatePaymentCommand validatePaymentMethod = ValidatePaymentCommand.builder()
                .cardDetails(user.getCardDetails())
                .orderId(user.getUserId())
                .paymentId(UUID.randomUUID().toString())
                .build();

        commandGateway.sendAndWait(validatePaymentMethod);
    }

    private void cancelOrderCommand(String orderId) {
        CancelOrderCommand cancelOrderCommand = new CancelOrderCommand(orderId);
        commandGateway.send(cancelOrderCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(PaymentProcessedEvent event) {

        log.info("PaymentProcessedEvent in saga for order Id : {}", event.getOrderId());

        try {
            ShipOrderCommand shipOrderCommand = ShipOrderCommand.builder()
                    .shipmentId(UUID.randomUUID().toString())
                    .orderId(event.getOrderId())
                    .build();

            commandGateway.send(shipOrderCommand);

        } catch (Exception e) {
            log.error(e.getMessage());

            //Start the compensating transaction again if fails
            cancelPaymentCommand(event);
        }
    }

    private void cancelPaymentCommand(PaymentProcessedEvent event) {

        CancelPaymentCommand cancelPaymentCommand = new CancelPaymentCommand(event.getOrderId(), event.getPaymentId());
        commandGateway.send(cancelPaymentCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippedEvent event) {
        log.info("OrderShippedEvent in saga for order Id : {}", event.getOrderId());

        try {
            CompleteOrderCommand completeOrderCommand = CompleteOrderCommand.builder()
                    .orderId(event.getOrderId())
                    .orderStatus(AppUtil.orderStatus.APPROVED.getValue())
                    .build();

            commandGateway.send(completeOrderCommand);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCompletedEvent event) {
        log.info("OrderCompletedEvent in saga for order Id : {}", event.getOrderId());

    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCancelledEvent event) {
        log.info("OrderCancelledEvent in saga for order Id : {}", event.getOrderId());

    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentCancelledEvent event) {
        log.info("PaymentCancelledEvent in saga for order Id : {}", event.getOrderId());

        cancelOrderCommand(event.getOrderId());
    }

}
