package com.client.ws.rasmooplus.mapper;

import com.client.ws.rasmooplus.dto.PaymentProcessDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;

public class OrderMapper {

    public static OrderDto build(String customerId , PaymentProcessDto paymentProcessDto){

        return OrderDto.builder()
                .customerId(customerId)
                .productAcronomyn(paymentProcessDto.getProductKey())
                .discount(paymentProcessDto.getDiscount())
                .build();


    }
}
