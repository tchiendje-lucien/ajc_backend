package com.example.ajc_backend.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.CompetenceOffre;
import com.example.ajc_backend.entites.OffreEmplois;
import com.example.ajc_backend.services.interfaces.entreprises.CompetenceEmploisServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("*")
public class CompetenceEmploisController {

    @Autowired
    CompetenceEmploisServices competenceEmploisServices;

    @PostMapping(path = "/Create_competence", consumes = { "multipart/form-data" })
    public MessageResponse Create_competence(@RequestPart("competenceOffre") String competenceOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return competenceEmploisServices.Create_competence(
                new ObjectMapper().readValue(competenceOffre, CompetenceOffre.class));
    }

    @PutMapping(path = "/update_competence", consumes = { "multipart/form-data" })
    public MessageResponse update_competence(@RequestPart("competenceOffre") String competenceOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return competenceEmploisServices.update_competence(
                new ObjectMapper().readValue(competenceOffre, CompetenceOffre.class));
    }

    @PostMapping(path = "/delete_competence", consumes = { "multipart/form-data" })
    public MessageResponse delete_competence(@RequestPart("competenceOffre") String competenceOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return competenceEmploisServices.delete_competence(
                new ObjectMapper().readValue(competenceOffre, CompetenceOffre.class));
    }

    @PostMapping(path = "/list_competence", consumes = { "multipart/form-data" })
    public List<CompetenceOffre> list_competence(@RequestPart("offreEmplois") String offreEmplois)
            throws JsonMappingException, JsonProcessingException, IOException {
        return competenceEmploisServices.list_competence(
                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class));
    }
}
