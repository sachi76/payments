package com.scaler.payments.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentImplementation implements PaymentServices{

    @Override
    public String makePayment(String orderId, Long amount) throws StripeException {
        Stripe.apiKey = "sk_test_51PO18fCant4AfkO8PXWRkDqF4lGf0bLrCvkwvCJ6LJF52GScxA3sbBhtf5xOhv7uG355rMspAGNJAmqNynBTchxU00rZ2N80FV";
        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName(orderId).build()
                        )
                        .build();
        Price price = Price.create(params);


        PaymentLinkCreateParams paymentsParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://www.credwise.co.in/")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentsParams);
        return paymentLink.getUrl();
    }
}
