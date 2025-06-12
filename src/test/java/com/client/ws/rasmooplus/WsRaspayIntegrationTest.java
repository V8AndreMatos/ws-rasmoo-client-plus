package com.client.ws.rasmooplus.integration;

import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class WsRaspayIntegrationTest {

    @Autowired
    private WsRasPayIntegration wsRasPayIntegration;

    @Test
    void createCustomerWhenDtoOK(){

        CustomerDto dto = new CustomerDto(null, "48322335059" , "teste@teste" , "André" , "Matos");

        wsRasPayIntegration.createCustomer(dto);

    }


}
