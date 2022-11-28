package com.example.ajc_backend.services.interfaces.entreprises;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.RespoEntreprise;
import com.example.ajc_backend.entites.Users;

@Component
@Service
@CrossOrigin("*")
public interface EntrpriseAccountService {
        MessageResponse create_entreprise(EntrepriseAccount entrepriseAccount, List<RespoEntreprise> respoEntreprises,
                        Users users,
                        MultipartFile logo) throws IOException;

        MessageResponse create_entreprise_withoutLogo(EntrepriseAccount entrepriseAccount,
                        List<RespoEntreprise> respoEntreprises, Users users);

        MessageResponse update_entreprise(EntrepriseAccount entrepriseAccount, List<RespoEntreprise> respoEntreprises,
                        Users users,
                        MultipartFile logo) throws IOException;

        MessageResponse update_entreprise_withoutLogo(EntrepriseAccount entrepriseAccount,
                        List<RespoEntreprise> respoEntreprises, Users users);

        List<EntrepriseAccount> list_entrepriseAccounts();

        MessageResponse change_password(Users users);

        //Optional<EntrepriseAccount> find_entreprise();
}
