package com.sagapattern.orderService.command.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    private String productId;

    private String userId;

    private String addressId;

    private Integer quantity;
}
