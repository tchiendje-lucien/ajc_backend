package com.example.ajc_backend.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.dao.EntrepriseAccountRepository;
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
        @Autowired
        EntrepriseAccountRepository entrepriseAccountRepository;

        public static String fileDirectry = System.getProperty("user.home") + "/Documents/ajc-web-site/logo/";

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
                        @RequestPart("logo") MultipartFile logo)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return entrpriseAccountService.update_entreprise(
                                new ObjectMapper().readValue(entrepriseAccount, EntrepriseAccount.class),
                                logo);
        }

        @PutMapping(path = "/update_entreprise_withoutLogo", consumes = { "multipart/form-data" })
        public MessageResponse update_entreprise_withoutLogo(@RequestPart("entrepriseAccount") String entrepriseAccount)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return entrpriseAccountService.update_entreprise_withoutLogo(
                                new ObjectMapper().readValue(entrepriseAccount, EntrepriseAccount.class));
        }

        @GetMapping(path = "/list_entrepriseAccounts")
        public List<EntrepriseAccount> list_entrepriseAccounts()
                        throws JsonMappingException, JsonProcessingException, IOException {
                return entrpriseAccountService.list_entrepriseAccounts();
        }

        @GetMapping(path = "/imageEntreprise/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
        public byte[] imageEntreprise(@PathVariable(name = "id") Long id) throws Exception {
                // System.out.println("photoname");
                EntrepriseAccount entreprise = entrepriseAccountRepository.findById(id).get();
                String photoname = entreprise.getLogo();
                // System.out.println(photoname);
                // chemin repertoir utilisateur
                File file = new File(fileDirectry + photoname);
                Path path = Paths.get(file.toURI());
                return Files.readAllBytes(path);
        }

}
