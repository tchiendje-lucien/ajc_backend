package com.example.ajc_backend.services.interfaces.entreprises;

import java.util.List;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.CompetenceOffre;
import com.example.ajc_backend.entites.OffreEmplois;

public interface CompetenceEmploisServices {
    MessageResponse Create_competence(CompetenceOffre competenceOffre);

    MessageResponse update_competence(CompetenceOffre competenceOffre);

    MessageResponse delete_competence(CompetenceOffre competenceOffre);

    List<CompetenceOffre> list_competence(OffreEmplois offreEmplois);
}
