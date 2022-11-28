package com.example.ajc_backend.services.implementations;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.dao.UsersRepository;
import com.example.ajc_backend.entites.Users;
import com.example.ajc_backend.services.interfaces.UserService;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public class UserImplement implements UserService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users loadByUserName(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Users find_user(String jwtToken) {
        Gson g = new Gson();
        jwtToken = jwtToken.replace("Bearer ", "");
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        User user = g.fromJson(body, User.class);
        // System.out.println("JWT Body : " + user.getSub());
        return usersRepository.findByUsername(user.getSub());
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {

    private String sub;
    private String[] roles;
    private String iss;
    private String exp;

}
