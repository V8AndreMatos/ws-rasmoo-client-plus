package com.client.ws.rasmooplus.services.impl;

import com.client.ws.rasmooplus.WsRasPayIntegration;
import com.client.ws.rasmooplus.dto.PaymentProcessDto;
import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.ws.rasmooplus.exception.BusinessException;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.integration.MailIntegration;
import com.client.ws.rasmooplus.mapper.OrderMapper;
import com.client.ws.rasmooplus.mapper.UserPaymentInfoMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.CreditCardMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.CustomerMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.PaymentMapper;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserPaymentInfo;
import com.client.ws.rasmooplus.repository.UserPaymentInfoRepository;
import com.client.ws.rasmooplus.repository.UserRepository;
import com.client.ws.rasmooplus.services.PaymentInfoService;

import java.util.Objects;

public class PaymentInfoServiceImpl implements PaymentInfoService {

    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;

    private final WsRasPayIntegration wsRasPayIntegration;

    private final MailIntegration mailIntegration;

    PaymentInfoServiceImpl(UserRepository userRepository , UserPaymentInfoRepository userPaymentInfoRepository ,
                           WsRasPayIntegration wsRasPayIntegration ,
                           MailIntegration mailIntegration){
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
        this.wsRasPayIntegration = wsRasPayIntegration;
        this.mailIntegration = mailIntegration;

    }

    @Override
    public Boolean process(PaymentProcessDto dto) {
        //Verifica usuário por ID e se ja existe assinatura
        var userOpt = userRepository.findById(dto.getUserPaymentInfoDto().getUserId());
        if (userOpt.isEmpty()){
            throw new NotFoundException("Usuário não encontrado");
        }

        User user = userOpt.get();

        if(Objects.nonNull(user.getSubscriptionType())){
            throw new BusinessException("Pagemento não pode ser processado pois já possui assinatura");

        }
        // Cria ou atualiza usuário raspay
        CustomerDto customerDto = wsRasPayIntegration.createCustomer(CustomerMapper.build(user));
        //Cria o pedido de pagamento
        OrderDto orderDto = wsRasPayIntegration.createOrder(OrderMapper.build(customerDto.getId() , dto));

        //Processa e retorna o pagamento
        PaymentDto paymentDto  = PaymentMapper
                .build(customerDto.getId(), orderDto.getId(), CreditCardMapper.build(dto.getUserPaymentInfoDto(), user.getCpf()));
       Boolean successPaayment = wsRasPayIntegration.processPayment(paymentDto);

       if (Boolean.TRUE.equals(successPaayment)){
           //Salvar informações de pagamento
           UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.fromDtoToEntity(dto.getUserPaymentInfoDto(), user);
           userPaymentInfoRepository.save(userPaymentInfo);
           mailIntegration.send(user.getEmail() , "Usuario: " +user.getEmail()+ " Senha:dodgemagnum" , "Acesso Liberado");
           return true;
       }

        //Enviar e-mail de confirmação de compra
        //Retorna o sucesso ou não da compra


        return false;
    }
}
