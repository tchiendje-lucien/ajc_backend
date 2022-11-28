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
import com.example.ajc_backend.entites.MissionsOffre;
import com.example.ajc_backend.entites.OffreEmplois;
import com.example.ajc_backend.services.interfaces.entreprises.MissionOffreServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("*")
public class MissionOffreController {

    @Autowired
    MissionOffreServices missionOffreServices;

    @PostMapping(path = "/delete_mission", consumes = { "multipart/form-data" })
    public MessageResponse delete_mission(@RequestPart("missionsOffre") String missionsOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return missionOffreServices.delete_mission(
                new ObjectMapper().readValue(missionsOffre, MissionsOffre.class));
    }

    @PostMapping(path = "/Create_mission", consumes = { "multipart/form-data" })
    public MessageResponse Create_mission(@RequestPart("missionsOffre") String missionsOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return missionOffreServices.Create_mission(
                new ObjectMapper().readValue(missionsOffre, MissionsOffre.class));
    }

    @PutMapping(path = "/update_mission", consumes = { "multipart/form-data" })
    public MessageResponse update_mission(@RequestPart("missionsOffre") String missionsOffre)
            throws JsonMappingException, JsonProcessingException, IOException {
        return missionOffreServices.update_mission(
                new ObjectMapper().readValue(missionsOffre, MissionsOffre.class));
    }

    @PostMapping(path = "/list_mission", consumes = { "multipart/form-data" })
    public List<MissionsOffre> list_mission(@RequestPart("offreEmplois") String offreEmplois)
            throws JsonMappingException, JsonProcessingException, IOException {
        return missionOffreServices.list_mission(
                new ObjectMapper().readValue(offreEmplois, OffreEmplois.class));
    }
}
