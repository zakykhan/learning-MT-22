package com.sagapattern.orderService.command.api.controller;

import com.sagapattern.commonService.AppUtil.AppUtil;
import com.sagapattern.orderService.command.api.command.OrderCreateCommand;
import com.sagapattern.orderService.command.api.dto.OrderRequestDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    private CommandGateway commandGateway;

    @PostMapping("/create")
    public String createOrder(@RequestBody OrderRequestDto orderRequest){
        String orderId = UUID.randomUUID().toString();
        OrderCreateCommand orderCreateCommand = OrderCreateCommand.builder()
                .orderId(orderId)
                .addressId(orderRequest.getAddressId())
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .orderStatus(AppUtil.orderStatus.CREATED.getValue())
                .build();

        commandGateway.sendAndWait(orderCreateCommand);

        return "Order created";
    }
}
