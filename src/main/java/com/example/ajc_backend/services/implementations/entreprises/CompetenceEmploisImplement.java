package com.example.ajc_backend.services.implementations.entreprises;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.dao.CompetenceOffreRepository;
import com.example.ajc_backend.entites.CompetenceOffre;
import com.example.ajc_backend.entites.OffreEmplois;
import com.example.ajc_backend.services.interfaces.entreprises.CompetenceEmploisServices;

@Service
public class CompetenceEmploisImplement implements CompetenceEmploisServices {

    @Autowired
    CompetenceOffreRepository competenceOffreRepository;

    @Override
    public MessageResponse Create_competence(CompetenceOffre competenceOffre) {
        competenceOffre.setName(competenceOffre.getName());
        competenceOffreRepository.save(competenceOffre);
        return new MessageResponse("Competence creer avec success", true);
    }

    @Override
    public MessageResponse update_competence(CompetenceOffre competenceOffre) {
        Optional<CompetenceOffre> get_competence = competenceOffreRepository.findById(competenceOffre.getOid());
        if (get_competence.isPresent()) {
            get_competence.get().setName(competenceOffre.getName());
            competenceOffreRepository.save(get_competence.get());
            return new MessageResponse("Competence modifier avec success", true);
        } else {
            return new MessageResponse("La competence recherchée n'existe pas", false);
        }
    }

    @Override
    public MessageResponse delete_competence(CompetenceOffre competenceOffre) {
        competenceOffreRepository.deleteById(competenceOffre.getOid());
        return new MessageResponse("Competence supprimée avec success", true);
    }

    @Override
    public List<CompetenceOffre> list_competence(OffreEmplois offreEmplois) {
        return competenceOffreRepository.findByOffreEmplois(offreEmplois);
    }

}
