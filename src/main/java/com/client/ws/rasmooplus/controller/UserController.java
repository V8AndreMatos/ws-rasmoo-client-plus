package com.client.ws.rasmooplus.controller;


import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDto dto){

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }


}
