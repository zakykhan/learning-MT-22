package com.sagapattern.commonService.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDetails {

    private String cardNumber;

    private String name;

    private Integer validTillMonth;

    private Integer validUntilYear;

    private Integer cvv;
}
