package com.example.ajc_backend.services.interfaces.entreprises;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.DemandeProfil;
import com.example.ajc_backend.entites.EntrepriseAccount;

@Component
@Service
@CrossOrigin("*")
public interface DemandeProfilService {
    MessageResponse create_ask_profile(DemandeProfil demandeProfil);

    MessageResponse update_ask_profile(DemandeProfil demandeProfil);

    MessageResponse delete_ask_profile(DemandeProfil demandeProfil);

    List<DemandeProfil> list_ask_profile(EntrepriseAccount entrepriseAccount);

    Optional<DemandeProfil> edit_ask_profile(Long oid_AkProfile);
}
