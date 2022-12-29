package com.example.ajc_backend.services.implementations.entreprises;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.dao.ChopperRepository;
import com.example.ajc_backend.entites.Chopper;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.services.interfaces.entreprises.ChopperService;


@Service
public class ChopperImplement implements ChopperService {

    @Autowired
    ChopperRepository chopperRepository;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @Override
    public MessageResponse create_chopper(Chopper chopper) {
        chopper.setCancel_at(null);
        chopper.setCandidat(chopper.getCandidat());
        chopper.setCreated_at(dtf.format(now));
        chopper.setEntrepriseAccount(chopper.getEntrepriseAccount());
        chopperRepository.save(chopper);
        return new MessageResponse("Le profil a été choppé", true);
    }

    @Override
    public MessageResponse cancel_chopper(Chopper chopper) {
        Optional<Chopper> get_chopper = chopperRepository.findById(chopper.getOid());
        if (get_chopper.isPresent()) {
            get_chopper.get().setDelete(true);
            get_chopper.get().setCancel_at(dtf.format(now));
            chopperRepository.save(get_chopper.get());
            return new MessageResponse("La demande de profil a été annulé", true);
        } else {
            return new MessageResponse("Le profil rechercher n'existe pas", false);
        }
    }

    @Override
    public List<Chopper> list_chopper(EntrepriseAccount entrepriseAccount) {
        return chopperRepository.listMyChopperProfile(entrepriseAccount);
    }

}
