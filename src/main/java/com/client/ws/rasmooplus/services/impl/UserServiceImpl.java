package com.client.ws.rasmooplus.services.impl;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.mapper.UserMapper;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserType;
import com.client.ws.rasmooplus.repository.UserRepository;
import com.client.ws.rasmooplus.repository.UserTypeRepository;
import com.client.ws.rasmooplus.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserTypeRepository userTypeRepository;

   public UserServiceImpl(UserRepository userRepository , UserTypeRepository userTypeRepository ){
            this.userRepository = userRepository;
            this.userTypeRepository = userTypeRepository;
    }


    @Override
    public User create(UserDto dto) {

       if(Objects.nonNull(dto.getId())){
            throw new BadRequestException("id deve ser nulo");
      }

       var userTypeOpt = userTypeRepository.findAllById(dto.getUserTypeId());

       if (userTypeOpt.isEmpty()){
            throw new NotFoundException("userTypeId não encontrado");
       }

        UserType userType = userTypeOpt.get();
       User user = UserMapper.fromDtoToEntity(dto, userType, null);

        return userRepository.save(user);
    }
}
