package com.client.ws.rasmooplus.mapper.wsraspay;

import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.model.User;

public class CustomerMapper {

    public static CustomerDto build(User user){

        var fullName = user.getName().split(" ");
        var fisrtName = fullName[0];
        var lastName = fullName.length > 1 ? fullName[fullName.length -1] : "";

        return CustomerDto.builder()
                .email(user.getEmail())
                .cpf(user.getCpf())
                .firstName(fisrtName)
                .lastName(lastName)
                .build();

    }
}
