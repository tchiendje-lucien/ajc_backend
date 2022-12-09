package com.example.ajc_backend.services.interfaces.entreprises;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.entites.ActivitySectors;
import com.example.ajc_backend.entites.CompetenceOffre;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.MissionsOffre;
import com.example.ajc_backend.entites.OffreEmplois;
import com.example.ajc_backend.entites.PostulerOffre;

public interface OffreServices {

        // Offres services
        MessageResponse create_offre(OffreEmplois offreEmplois, List<MissionsOffre> missionsOffres,
                        List<CompetenceOffre> competenceOffres, MultipartFile image) throws IOException;

        MessageResponse create_withoutImage(OffreEmplois offreEmplois, List<MissionsOffre> missionsOffres,
                        List<CompetenceOffre> competenceOffres);

        MessageResponse update_offre(OffreEmplois offreEmplois, MultipartFile image) throws IOException;

        MessageResponse update_withoutImage(OffreEmplois offreEmplois);

        Optional<OffreEmplois> find_offre(Long oid_offre);

        List<OffreEmplois> list_offre();

        void getImageOffre(String filename, HttpServletResponse response);

        List<OffreEmplois> list_similary_offre(ActivitySectors activitySectors, String pays);

        MessageResponse postuler_offre(PostulerOffre postulerOffre);

        List<OffreEmplois> list_my_offre(EntrepriseAccount entrepriseAccount);

        MessageResponse supprimer_offre(OffreEmplois offreEmplois);

}
