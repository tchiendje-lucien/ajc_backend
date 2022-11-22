package com.example.ajc_backend.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.CompetenceOffre;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.MissionsOffre;
import com.example.ajc_backend.entites.OffreEmplois;
import com.example.ajc_backend.services.interfaces.entreprises.OffreServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("*")
public class OffreController {
    @Autowired
    OffreServices offreServices;

    @PostMapping(path = "create_offre", consumes = { "multipart/form-data" })
    public MessageResponse create_offre(@RequestPart("offreEmplois") String offreEmplois,
            @RequestPart("missionsOffres") String missionsOffres,
            @RequestPart("competenceOffres") String competenceOffres,
            @RequestPart("image") MultipartFile image)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.create_offre(
                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class),
                new ObjectMapper().readValue(missionsOffres,
                        new TypeReference<ArrayList<MissionsOffre>>() {
                        }),
                new ObjectMapper().readValue(competenceOffres,
                        new TypeReference<ArrayList<CompetenceOffre>>() {
                        }),
                image);
    }

    @PostMapping(path = "create_withoutImage", consumes = { "multipart/form-data" })
    public MessageResponse create_withoutImage(@RequestPart("offreEmplois") String offreEmplois,
            @RequestPart("missionsOffres") String missionsOffres,
            @RequestPart("competenceOffres") String competenceOffres)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.create_withoutImage(
                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class),
                new ObjectMapper().readValue(missionsOffres,
                        new TypeReference<ArrayList<MissionsOffre>>() {
                        }),
                new ObjectMapper().readValue(competenceOffres,
                        new TypeReference<ArrayList<CompetenceOffre>>() {
                        }));
    }

    @PutMapping(path = "update_offre", consumes = { "multipart/form-data" })
    public MessageResponse update_offre(@RequestPart("offreEmplois") String offreEmplois,
            @RequestPart("image") MultipartFile image)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.update_offre(
                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class), image);
    }

    @PutMapping(path = "update_withoutImage", consumes = { "multipart/form-data" })
    public MessageResponse update_withoutImage(@RequestPart("offreEmplois") String offreEmplois)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.update_withoutImage(
                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class));
    }

    @GetMapping(value = "find_offre/{oid_offre}")
    public Optional<OffreEmplois> find_offre(@PathVariable Long oid_offre) {
        return offreServices.find_offre(oid_offre);
    }

    @PostMapping(path = "list_offre", consumes = { "multipart/form-data" })
    public List<OffreEmplois> list_offre(@RequestPart("entrepriseAccount") String entrepriseAccount)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.list_offre(
                new ObjectMapper().readValue(entrepriseAccount, EntrepriseAccount.class));
    }

    @PostMapping(path = "delete_mission", consumes = { "multipart/form-data" })
    public MessageResponse delete_mission(@RequestPart("missionsOffre") String missionsOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.delete_mission(
                new ObjectMapper().readValue(missionsOffre, MissionsOffre.class));
    }

    @PostMapping(path = "Create_mission", consumes = { "multipart/form-data" })
    public MessageResponse Create_mission(@RequestPart("missionsOffre") String missionsOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.Create_mission(
                new ObjectMapper().readValue(missionsOffre, MissionsOffre.class));
    }

    @PutMapping(path = "update_mission", consumes = { "multipart/form-data" })
    public MessageResponse update_mission(@RequestPart("missionsOffre") String missionsOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.update_mission(
                new ObjectMapper().readValue(missionsOffre, MissionsOffre.class));
    }

    @PostMapping(path = "list_mission", consumes = { "multipart/form-data" })
    public List<MissionsOffre> list_mission(@RequestPart("offreEmplois") String offreEmplois)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.list_mission(
                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class));
    }

    @PostMapping(path = "Create_competence", consumes = { "multipart/form-data" })
    public MessageResponse Create_competence(@RequestPart("competenceOffre") String competenceOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.Create_competence(
                new ObjectMapper().readValue(competenceOffre, CompetenceOffre.class));
    }

    @PutMapping(path = "update_competence", consumes = { "multipart/form-data" })
    public MessageResponse update_competence(@RequestPart("competenceOffre") String competenceOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.update_competence(
                new ObjectMapper().readValue(competenceOffre, CompetenceOffre.class));
    }

    @PostMapping(path = "delete_competence", consumes = { "multipart/form-data" })
    public MessageResponse delete_competence(@RequestPart("competenceOffre") String competenceOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.delete_competence(
                new ObjectMapper().readValue(competenceOffre, CompetenceOffre.class));
    }

    @PostMapping(path = "list_competence", consumes = { "multipart/form-data" })
    public List<CompetenceOffre> list_competence(@RequestPart("offreEmplois") String offreEmplois)
            throws JsonMappingException, JsonProcessingException, IOException {
        return offreServices.list_competence(
                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class));
    }
}
