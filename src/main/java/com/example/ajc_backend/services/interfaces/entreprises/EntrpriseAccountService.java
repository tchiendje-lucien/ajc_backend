package com.example.ajc_backend.services.interfaces.entreprises;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.Pays;
import com.example.ajc_backend.entites.RespoEntreprise;

@Component
@Service
@CrossOrigin("*")
public interface EntrpriseAccountService {
        MessageResponse create_entreprise(EntrepriseAccount entrepriseAccount, List<RespoEntreprise> respoEntreprises,
                        Pays pays, MultipartFile logo) throws IOException;

        MessageResponse create_entreprise_withoutLogo(EntrepriseAccount entrepriseAccount,
                        List<RespoEntreprise> respoEntreprises,
                        Pays pays);

        MessageResponse update_entreprise(EntrepriseAccount entrepriseAccount, List<RespoEntreprise> respoEntreprises,
                        Pays pays, MultipartFile logo) throws IOException;

        MessageResponse update_entreprise_withoutLogo(EntrepriseAccount entrepriseAccount,
                        List<RespoEntreprise> respoEntreprises,
                        Pays pays);

        List<EntrepriseAccount> list_entrepriseAccounts();

        MessageResponse change_password(EntrepriseAccount entrepriseAccount);
}
