package com.scaler.payments.controller;

import com.scaler.payments.dtos.PaymentRequestDto;
import com.scaler.payments.services.PaymentServices;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentsController {

    PaymentServices paymentServices;

    public PaymentsController(PaymentServices paymentServices) {
        this.paymentServices = paymentServices;
    }

    @PostMapping("/payments")
    public ResponseEntity<String> createPaymentLink(@RequestBody PaymentRequestDto paymentRequestDto) throws StripeException {
        String paymentLink = paymentServices.makePayment(paymentRequestDto.getOrderId(), paymentRequestDto.getPaymentAmount());

        return new ResponseEntity<>(paymentLink, HttpStatus.OK);
    }

    @PostMapping("/webhook")
    public String handleWebhook()  {
        System.out.println("webhook request is received");
        return "";
    }

}
