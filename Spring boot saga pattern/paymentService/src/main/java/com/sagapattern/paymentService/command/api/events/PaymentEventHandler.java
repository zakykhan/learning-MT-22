package com.sagapattern.paymentService.command.api.events;

import com.sagapattern.commonService.events.PaymentCancelledEvent;
import com.sagapattern.commonService.events.PaymentProcessedEvent;
import com.sagapattern.paymentService.Dao.PaymentDao;
import com.sagapattern.paymentService.command.api.entity.Payment;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class PaymentEventHandler {
    private PaymentDao paymentDao;

    public PaymentEventHandler(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }


    @EventHandler
    public void  on(PaymentProcessedEvent event){

        Payment payment = Payment.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .timestamp(new Date())
                .build();

        paymentDao.save(payment);

    }

    @EventHandler
    public void on(PaymentCancelledEvent event){

        Optional<Payment> paymentObj = paymentDao.findById(event.getPaymentId());

        Payment payment = paymentObj.orElse(null);

        assert payment != null;
        paymentDao.save(payment);

    }
}
