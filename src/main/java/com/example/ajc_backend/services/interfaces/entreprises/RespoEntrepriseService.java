package com.example.ajc_backend.services.interfaces.entreprises;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.RespoEntreprise;

@Component
@Service
@CrossOrigin("*")
public interface RespoEntrepriseService {

    MessageResponse update_respo_entreprise(RespoEntreprise respoEntreprises);

    MessageResponse delete_respo_entreprise(RespoEntreprise respoEntreprises);

    MessageResponse create_respo_entreprise(RespoEntreprise respoEntreprises);

    List<RespoEntreprise> list_respoEntreprise(EntrepriseAccount entrepriseAccount);
}
