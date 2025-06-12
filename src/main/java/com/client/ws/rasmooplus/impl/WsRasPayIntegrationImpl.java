package com.client.ws.rasmooplus.integration.impl;

import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.ws.rasmooplus.integration.WsRasPayIntegration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Component
public class WsRasPayIntegrationImpl implements WsRasPayIntegration {

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;


    @Override
    public CustomerDto createCustomer(CustomerDto dto) {
            try {

                String credential = "rasmooplus:r@sm00";
                String base64 = new String(Base64.getEncoder(credential.getBytes()));
                httpHeaders.add("Authorization" , "Basic "+base64);
                HttpEntity<CustomerDto> request = new HttpEntity<>(dto , this.httpHeaders);
                restTemplate.exchange("http://localhost:8081/es-raspay/v1/customer",
                        HttpMethod.POST, request, CustomerDto.class);

                return response.getbody();

            }catch (Exception e){
                throw e ;

            }
    }

    public WsRasPayIntegrationImpl(){
        restTemplate = new RestTemplate();
        httpHeaders = getHttpHeaders();

    }



    @Override
    public OrderDto createOrder(OrderDto dto) {
        try {

           HttpEntity<OrderDto> request = new HttpEntity<>(dto , this.httpHeaders);
            restTemplate.exchange("http://localhost:8081/es-raspay/v1/customer",
                    HttpMethod.POST, request, CustomerDto.class);

            return response.getbody();

        }catch (Exception e){
            throw e ;

        }
    }

    @Override
    public Boolean processPayment(PaymentDto dto) {
        return null;
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String credential = "rasmooplus:r@sm00";
        String base64 = new String(Base64.getEncoder(credential.getBytes()));
        httpHeaders.add("Authorization" , "Basic "+base64);
        return httpHeaders;

    }

}
