package com.example.ajc_backend.services.interfaces.entreprises;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.Chopper;
import com.example.ajc_backend.entites.EntrepriseAccount;

@Component
@Service
@CrossOrigin("*")
public interface ChopperService {
    MessageResponse create_chopper(Chopper chopper);

    MessageResponse cancel_chopper(Chopper chopper);

    List<Chopper> list_chopper(EntrepriseAccount entrepriseAccount);
}
