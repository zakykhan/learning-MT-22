package com.sagapattern.paymentService.command.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    private String paymentId;

    private String orderId;

    private Date timestamp;

    private String paymentStatus;
}
