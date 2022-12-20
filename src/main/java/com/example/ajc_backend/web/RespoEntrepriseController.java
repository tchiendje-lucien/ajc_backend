package com.example.ajc_backend.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.RespoEntreprise;
import com.example.ajc_backend.services.interfaces.entreprises.RespoEntrepriseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin("*")
public class RespoEntrepriseController {

    @Autowired
    RespoEntrepriseService respoEntrepriseService;

    @PutMapping(path = "/update_respo_entreprise")
    public MessageResponse update_respo_entreprise(@RequestBody RespoEntreprise respoEntreprise)
            throws JsonMappingException, JsonProcessingException, IOException {
        return respoEntrepriseService.update_respo_entreprise(respoEntreprise);
    }

    @PostMapping(path = "/create_respo_entreprise")
    public MessageResponse create_respo_entreprise(@RequestBody RespoEntreprise respoEntreprise)
            throws JsonMappingException, JsonProcessingException, IOException {
        return respoEntrepriseService.create_respo_entreprise(respoEntreprise);
    }

    @PostMapping(path = "/delete_respo_entreprise")
    public MessageResponse delete_respo_entreprise(@RequestBody RespoEntreprise respoEntreprise)
            throws JsonMappingException, JsonProcessingException, IOException {
        return respoEntrepriseService.delete_respo_entreprise(respoEntreprise);
    }

    @PostMapping(path = "/list_respoEntreprise")
    public List<RespoEntreprise> list_respoEntreprise(@RequestBody EntrepriseAccount entrepriseAccount)
            throws JsonMappingException, JsonProcessingException, IOException {
        return respoEntrepriseService.list_respoEntreprise(entrepriseAccount);
    }
}
