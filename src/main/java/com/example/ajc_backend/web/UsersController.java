package com.example.ajc_backend.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.entites.Users;
import com.example.ajc_backend.services.interfaces.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin("*")
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping(path = "/find_user")
    public Users find_user(@RequestHeader(name = "Authorization") String token)
            throws JsonMappingException, JsonProcessingException, IOException {
        System.out.print(15252);
        return userService.find_user(token);
    }

    @GetMapping("/verified_token")
    public Users verified_token() {
        return null;
    }
}
