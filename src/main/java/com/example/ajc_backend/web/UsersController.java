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
@CrossOrigin (origins = "*" , exposedHeaders = "**")
public class UsersController {
    @Autowired
    UserService userService;

    // @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    @GetMapping(path = "/find_user")
    public Users find_user(@RequestHeader(name = "Authorization") String token)
            throws JsonMappingException, JsonProcessingException, IOException {
                System.out.print(15252);
        return userService.find_user(token);
    }

    // @GetMapping("/find_user")
    // public Users find_user(HttpServletRequest  head){
    //             System.out.print("head " + head.getHeader("Authorization"));
    //     return null;
    // }
}
