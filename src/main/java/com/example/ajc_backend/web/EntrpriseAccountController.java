package com.example.ajc_backend.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.dao.UsersRepository;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.RespoEntreprise;
import com.example.ajc_backend.entites.Users;
import com.example.ajc_backend.services.interfaces.entreprises.EntrpriseAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("*")
public class EntrpriseAccountController {

        @Autowired
        EntrpriseAccountService entrpriseAccountService;
        @Autowired
        UsersRepository usersRepository;

        @PostMapping(path = "/create_entreprise", consumes = { "multipart/form-data" })
        public MessageResponse create_entreprise(@RequestPart("entrepriseAccount") String entrepriseAccount,
                        @RequestPart("respoEntreprises") String respoEntreprises, @RequestPart("users") String users,
                        @RequestPart("logo") MultipartFile logo)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return entrpriseAccountService.create_entreprise(
                                new ObjectMapper().readValue(entrepriseAccount, EntrepriseAccount.class),
                                new ObjectMapper().readValue(respoEntreprises,
                                                new TypeReference<ArrayList<RespoEntreprise>>() {
                                                }),
                                new ObjectMapper().readValue(users, Users.class),
                                logo);
        }

        @PostMapping(path = "/create_entreprise_withoutLogo", consumes = { "multipart/form-data" })
        public MessageResponse create_entreprise_withoutLogo(@RequestPart("entrepriseAccount") String entrepriseAccount,
                        @RequestPart("respoEntreprises") String respoEntreprises, @RequestPart("users") String users)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return entrpriseAccountService.create_entreprise_withoutLogo(
                                new ObjectMapper().readValue(entrepriseAccount, EntrepriseAccount.class),
                                new ObjectMapper().readValue(respoEntreprises,
                                                new TypeReference<ArrayList<RespoEntreprise>>() {
                                                }),
                                new ObjectMapper().readValue(users, Users.class));
        }

        @PutMapping(path = "/update_entreprise", consumes = { "multipart/form-data" })
        public MessageResponse update_entreprise(@RequestPart("entrepriseAccount") String entrepriseAccount,
                        @RequestPart("respoEntreprises") String respoEntreprises, @RequestPart("users") String users,
                        @RequestPart("logo") MultipartFile logo)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return entrpriseAccountService.update_entreprise(
                                new ObjectMapper().readValue(entrepriseAccount, EntrepriseAccount.class),
                                new ObjectMapper().readValue(respoEntreprises,
                                                new TypeReference<ArrayList<RespoEntreprise>>() {
                                                }),
                                new ObjectMapper().readValue(users, Users.class),
                                logo);
        }

        @PutMapping(path = "/update_entreprise_withoutLogo", consumes = { "multipart/form-data" })
        public MessageResponse update_entreprise_withoutLogo(@RequestPart("entrepriseAccount") String entrepriseAccount,
                        @RequestPart("respoEntreprises") String respoEntreprises, @RequestPart("users") String users)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return entrpriseAccountService.update_entreprise_withoutLogo(
                                new ObjectMapper().readValue(entrepriseAccount, EntrepriseAccount.class),
                                new ObjectMapper().readValue(respoEntreprises,
                                                new TypeReference<ArrayList<RespoEntreprise>>() {
                                                }),
                                new ObjectMapper().readValue(users, Users.class));
        }

        @GetMapping(path = "/list_entrepriseAccounts")
        public List<EntrepriseAccount> list_entrepriseAccounts()
                        throws JsonMappingException, JsonProcessingException, IOException {
                return entrpriseAccountService.list_entrepriseAccounts();
        }

        @PutMapping(path = "/change_password", consumes = { "multipart/form-data" })
        public MessageResponse change_password(@RequestPart("users") String users)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return entrpriseAccountService.change_password(
                                new ObjectMapper().readValue(users, Users.class));
        }

}
