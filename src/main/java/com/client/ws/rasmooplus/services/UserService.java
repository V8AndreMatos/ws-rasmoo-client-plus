package com.client.ws.rasmooplus.services;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.model.User;

public interface UserService {

        User create(UserDto dto);
}
