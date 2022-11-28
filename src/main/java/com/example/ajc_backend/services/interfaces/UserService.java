package com.example.ajc_backend.services.interfaces;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ajc_backend.entites.Users;

@Component
@Service
@CrossOrigin("*")
public interface UserService {

    Users loadByUserName(String username);

    Users find_user(String token);
}
