package com.scaler.payments.services;


import com.stripe.exception.StripeException;

public interface PaymentServices {
    String makePayment(String orderId, Long amount) throws StripeException;
}
