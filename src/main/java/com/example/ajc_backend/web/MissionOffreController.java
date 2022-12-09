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
import com.example.ajc_backend.entites.MissionsOffre;
import com.example.ajc_backend.entites.OffreEmplois;
import com.example.ajc_backend.services.interfaces.entreprises.MissionOffreServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin("*")
public class MissionOffreController {

        @Autowired
        MissionOffreServices missionOffreServices;

        @PostMapping(path = "/delete_mission")
        public MessageResponse delete_mission(@RequestBody MissionsOffre missionsOffre)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return missionOffreServices.delete_mission(missionsOffre);
        }

        @PostMapping(path = "/create_mission")
        public MessageResponse Create_mission(@RequestBody MissionsOffre missionsOffre)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return missionOffreServices.Create_mission(missionsOffre);
        }

        @PutMapping(path = "/update_mission")
        public MessageResponse update_mission(@RequestBody MissionsOffre missionsOffre)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return missionOffreServices.update_mission(missionsOffre);
        }

        @PostMapping(path = "/list_mission")
        public List<MissionsOffre> list_mission(@RequestBody OffreEmplois offreEmplois)
                        throws JsonMappingException, JsonProcessingException, IOException {
                return missionOffreServices.list_mission(offreEmplois);
        }
}
// Développer le front-end et le back-ends