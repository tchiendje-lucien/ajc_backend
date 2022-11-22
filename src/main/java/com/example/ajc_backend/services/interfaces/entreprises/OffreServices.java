package com.example.ajc_backend.services.interfaces.entreprises;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.CompetenceOffre;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.MissionsOffre;
import com.example.ajc_backend.entites.OffreEmplois;

public interface OffreServices {

        // Offres services
        MessageResponse create_offre(OffreEmplois offreEmplois, List<MissionsOffre> missionsOffres,
                        List<CompetenceOffre> competenceOffres, MultipartFile image) throws IOException;

        MessageResponse create_withoutImage(OffreEmplois offreEmplois, List<MissionsOffre> missionsOffres,
                        List<CompetenceOffre> competenceOffres);

        MessageResponse update_offre(OffreEmplois offreEmplois, MultipartFile image) throws IOException;

        MessageResponse update_withoutImage(OffreEmplois offreEmplois);

        Optional<OffreEmplois> find_offre(Long oid_offre);

        List<OffreEmplois> list_offre(EntrepriseAccount entrepriseAccount);

        // Missions services
        MessageResponse delete_mission(MissionsOffre missionsOffre);

        MessageResponse Create_mission(MissionsOffre missionsOffre);

        MessageResponse update_mission(MissionsOffre missionsOffre);

        List<MissionsOffre> list_mission(OffreEmplois offreEmplois);

        // Competence Services
        MessageResponse Create_competence(CompetenceOffre competenceOffre);

        MessageResponse update_competence(CompetenceOffre competenceOffre);

        MessageResponse delete_competence(CompetenceOffre competenceOffre);

        List<CompetenceOffre> list_competence(OffreEmplois offreEmplois);
}
