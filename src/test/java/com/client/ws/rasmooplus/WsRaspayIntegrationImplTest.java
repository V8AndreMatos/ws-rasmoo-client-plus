package com.client.ws.rasmooplus;

import com.client.ws.rasmooplus.dto.wsraspay.CreditCardDto;
import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
 class WsRaspayIntegrationImplTest {

    @Autowired
    private WsRasPayIntegration wsRasPayIntegration;

    @Test
    void createCustomerWhenDtoOK(){

        CustomerDto dto = new CustomerDto(null, "48322335059" , "teste@teste" , "André" , "Matos");

        wsRasPayIntegration.createCustomer(dto);

    }

    @Test
    void createOrderWhenDtoOK(){

        OrderDto dto = new OrderDto(null , "", BigDecimal.ZERO , "MONTH22" );

        wsRasPayIntegration.createOrder(dto);

    }

    @Test
    void processPaymentWhenDtoOK(){

        CreditCardDto creditCardDto = new CreditCardDto(123L , "00998877665" , 0L , 06L, "1234123412341234", 2025L);

        PaymentDto paymentDto = new PaymentDto(creditCardDto , "" , "");

        wsRasPayIntegration.processPayment(paymentDto);

    }


}
