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
import com.example.ajc_backend.entites.CompetenceOffre;
import com.example.ajc_backend.entites.OffreEmplois;
import com.example.ajc_backend.services.interfaces.entreprises.CompetenceEmploisServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin("*")
public class CompetenceEmploisController {

        @Autowired
        CompetenceEmploisServices competenceEmploisServices;

        @PostMapping(path = "/create_competence")
        public MessageResponse Create_competence(@RequestBody CompetenceOffre competenceOffre)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return competenceEmploisServices.Create_competence(competenceOffre);
        }

        @PutMapping(path = "/update_competence")
        public MessageResponse update_competence(@RequestBody CompetenceOffre competenceOffre)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return competenceEmploisServices.update_competence(competenceOffre);
        }

        @PostMapping(path = "/delete_competence")
        public MessageResponse delete_competence(@RequestBody CompetenceOffre competenceOffre)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return competenceEmploisServices.delete_competence(competenceOffre);
        }

        @PostMapping(path = "/list_competence")
        public List<CompetenceOffre> list_competence(@RequestBody OffreEmplois offreEmplois)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return competenceEmploisServices.list_competence(offreEmplois);
        }
}
