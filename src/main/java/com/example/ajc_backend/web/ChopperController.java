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
import com.example.ajc_backend.entites.Chopper;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.services.interfaces.entreprises.ChopperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin("*")
public class ChopperController {

    @Autowired
    ChopperService chopperService;

    @PostMapping(path = "/create_chopper")
    public MessageResponse create_chopper(@RequestBody Chopper chopper)
            throws JsonMappingException, JsonProcessingException, IOException {
        return chopperService.create_chopper(chopper);
    }

    @PutMapping(path = "/cancel_chopper")
    public MessageResponse cancel_chopper(@RequestBody Chopper chopper)
            throws JsonMappingException, JsonProcessingException, IOException {
        return chopperService.cancel_chopper(chopper);
    }

    @PostMapping(path = "/list_chopper")
    public List<Chopper> list_chopper(@RequestBody EntrepriseAccount entrepriseAccount)
            throws JsonMappingException, JsonProcessingException, IOException {
        return chopperService.list_chopper(entrepriseAccount);
    }

}
