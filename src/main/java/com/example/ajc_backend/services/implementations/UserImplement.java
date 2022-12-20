package com.example.ajc_backend.services.implementations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.MessageResponse;
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

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
    LocalDateTime now = LocalDateTime.now();

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

    @Override
    public MessageResponse update_username(Users users) {
        // Gson g = new Gson();
        // jwtToken = jwtToken.replace("Bearer ", "");
        // String[] split_string = jwtToken.split("\\.");
        // String base64EncodedBody = split_string[1];
        // Base64 base64Url = new Base64(true);
        // String body = new String(base64Url.decode(base64EncodedBody));
        // User user = g.fromJson(body, User.class);
        Optional<Users> get_user = usersRepository.findById(users.getOid());
        if (get_user.isPresent()) {
            if (usersRepository.findByIdentifiant(users.getIdentifiant()).isPresent()) {
                return new MessageResponse("Cet identifaint est deja utilisé", false);
            } else {
                get_user.get().setIdentifiant(users.getIdentifiant());
                get_user.get().setUpdated_at(dtf.format(now));
                usersRepository.save(get_user.get());
                return new MessageResponse("Votre identifiant a été modifié avec success", true);
            }
        } else {
            return new MessageResponse("L'identifaint recherché n'existe pas", false);
        }
    }

    @Override
    public MessageResponse change_password(Users users) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Gson g = new Gson();
        // jwtToken = jwtToken.replace("Bearer ", "");
        // String[] split_string = jwtToken.split("\\.");
        // String base64EncodedBody = split_string[1];
        // Base64 base64Url = new Base64(true);
        // String body = new String(base64Url.decode(base64EncodedBody));
        // User user = g.fromJson(body, User.class);
        Optional<Users> get_user = usersRepository.findById(users.getOid());

        if (get_user.isPresent()) {
            if (passwordEncoder.matches(users.getPassword(), get_user.get().getPassword())) {
                get_user.get().setPassword(passwordEncoder.encode(users.getRe_password()));
                usersRepository.save(get_user.get());
                return new MessageResponse("Mot de passe mit a jour avec success", true);
            } else {
                return new MessageResponse("Mot de passe different", false);
            }
        } else {
            return new MessageResponse("Cet utilisateur n'existe pas", false);
        }
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
