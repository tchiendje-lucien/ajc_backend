
package com.example.ajc_backend.services.implementations.entreprises;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.dao.RespoEntrepriseRepository;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.RespoEntreprise;
import com.example.ajc_backend.services.interfaces.entreprises.RespoEntrepriseService;

@Service
public class RespoEntrepriseImplement implements RespoEntrepriseService {

    @Autowired
    RespoEntrepriseRepository respoEntrepriseRepository;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
    LocalDateTime now = LocalDateTime.now();

    @Override
    public MessageResponse update_respo_entreprise(RespoEntreprise respoEntreprises) {

        // Update Responsable entreprise
        Optional<RespoEntreprise> get_respo = respoEntrepriseRepository
                .findById(respoEntreprises.getOid());
        if (get_respo.isPresent()) {
            get_respo.get().setEmail(respoEntreprises.getEmail());
            get_respo.get().setCivility(respoEntreprises.getCivility());
            get_respo.get().setUpdated_at(dtf.format(now));
            get_respo.get().setFunction(respoEntreprises.getFunction());
            get_respo.get().setName(respoEntreprises.getName());
            get_respo.get().setNumFixe(respoEntreprises.getNumFixe());
            get_respo.get().setNumPortable(respoEntreprises.getNumPortable());
            get_respo.get().setSurname(respoEntreprises.getSurname());
            respoEntrepriseRepository.save(get_respo.get());

            return new MessageResponse("Les informations du responsable ont été Modifié avec success", true);
        } else {
            return new MessageResponse("Le responsable recherché n'existe pas", false);
        }
    }

    @Override
    public MessageResponse delete_respo_entreprise(RespoEntreprise respoEntreprises) {
        respoEntrepriseRepository.deleteById(respoEntreprises.getOid());
        return new MessageResponse("Le responsable selectionné a été supprimé avec success", true);
    }

    @Override
    public MessageResponse create_respo_entreprise(RespoEntreprise respoEntreprises) {
        respoEntreprises.setEmail(respoEntreprises.getEmail());
        respoEntreprises.setCivility(respoEntreprises.getCivility());
        respoEntreprises.setCreated_at(dtf.format(now));
        respoEntreprises.setFunction(respoEntreprises.getFunction());
        respoEntreprises.setName(respoEntreprises.getName());
        respoEntreprises.setNumFixe(respoEntreprises.getNumFixe());
        respoEntreprises.setNumPortable(respoEntreprises.getNumPortable());
        respoEntreprises.setSurname(respoEntreprises.getSurname());
        respoEntreprises.setEntrepriseAccount(respoEntreprises.getEntrepriseAccount());
        respoEntrepriseRepository.save(respoEntreprises);

        return new MessageResponse("Responsable creer avec success", true);
    }

    @Override
    public List<RespoEntreprise> list_respoEntreprise(EntrepriseAccount entrepriseAccount) {
        return respoEntrepriseRepository.findByEntrepriseAccount(entrepriseAccount);
    }
}
