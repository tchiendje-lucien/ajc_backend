package com.example.ajc_backend.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.DemandeProfil;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.services.interfaces.entreprises.DemandeProfilService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin("*")
public class DemandeProfilController {

    @Autowired
    DemandeProfilService demandeProfilService;

    @PostMapping(path = "/create_ask_profile")
    public MessageResponse create_ask_profile(@RequestBody DemandeProfil demandeProfil)
            throws JsonMappingException, JsonProcessingException, IOException {
        return demandeProfilService.create_ask_profile(demandeProfil);
    }

    @PutMapping(path = "/update_ask_profile")
    public MessageResponse update_ask_profile(@RequestBody DemandeProfil demandeProfil)
            throws JsonMappingException, JsonProcessingException, IOException {
        return demandeProfilService.update_ask_profile(demandeProfil);
    }

    @PutMapping(path = "/delete_ask_profile")
    public MessageResponse delete_ask_profile(@RequestBody DemandeProfil demandeProfil)
            throws JsonMappingException, JsonProcessingException, IOException {
        return demandeProfilService.delete_ask_profile(demandeProfil);
    }

    @PostMapping(path = "/list_ask_profile")
    public List<DemandeProfil> list_ask_profile(@RequestBody EntrepriseAccount entrepriseAccount)
            throws JsonMappingException, JsonProcessingException, IOException {
        return demandeProfilService.list_ask_profile(entrepriseAccount);
    }

    @GetMapping(path = "/edit_ask_profile/{oid_AkProfile}")
    public Optional<DemandeProfil> edit_ask_profile(@PathVariable Long oid_AkProfile)
            throws JsonMappingException, JsonProcessingException, IOException {
        return demandeProfilService.edit_ask_profile(oid_AkProfile);
    }

}
