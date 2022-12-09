package com.example.ajc_backend.services.implementations.entreprises;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.dao.CompetenceOffreRepository;
import com.example.ajc_backend.dao.MissionsOffreRepository;
import com.example.ajc_backend.dao.OffreEmploisRepository;
import com.example.ajc_backend.dao.PostulerOffreRepository;
import com.example.ajc_backend.entites.ActivitySectors;
import com.example.ajc_backend.entites.CompetenceOffre;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.MissionsOffre;
import com.example.ajc_backend.entites.OffreEmplois;
import com.example.ajc_backend.entites.PostulerOffre;
import com.example.ajc_backend.services.interfaces.entreprises.OffreServices;

@Service
public class OffresImplement implements OffreServices {

    @Autowired
    OffreEmploisRepository offreEmploisRepository;
    @Autowired
    MissionsOffreRepository missionsOffreRepository;
    @Autowired
    CompetenceOffreRepository competenceOffreRepository;
    @Autowired
    PostulerOffreRepository postulerOffreRepository;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
    LocalDateTime now = LocalDateTime.now();
    public static String fileDirectry = "C:/Users/l.tchiendje/Documents/ajc-web-site/image_offre/";

    @Transactional
    @Override
    public MessageResponse create_offre(OffreEmplois offreEmplois, List<MissionsOffre> missionsOffres,
            List<CompetenceOffre> competenceOffres, MultipartFile image) throws IOException {

        // Upload image
        int index = image.getOriginalFilename().lastIndexOf('.');
        String extension = image.getOriginalFilename().substring(index + 1);
        String filename = "ajsearch_image_" + offreEmplois.getEntrepriseAccount().getOid()
                + "_"
                + dtf.format(now).toString() + "." + extension;
        byte[] bytes = image.getBytes();
        String insPath = fileDirectry + filename;
        Files.write(Paths.get(insPath), bytes);

        // Save new Offres
        offreEmplois.setActivitySectors(offreEmplois.getActivitySectors());
        offreEmplois.setCreated_at(dtf.format(now));
        offreEmplois.setDateline(offreEmplois.getDateline());
        offreEmplois.setDelete(false);
        offreEmplois.setDetail(offreEmplois.getDetail());
        offreEmplois.setEntrepriseAccount(offreEmplois.getEntrepriseAccount());
        offreEmplois.setNbPlace(offreEmplois.getNbPlace());
        offreEmplois.setState(true);
        offreEmplois.setTitle(offreEmplois.getTitle());
        offreEmplois.setTypeContrat(offreEmplois.getTypeContrat());
        offreEmplois.setTypeEmploi(offreEmplois.getTypeEmploi());
        offreEmplois.setVille(offreEmplois.getVille());
        offreEmplois.setPays(offreEmplois.getPays());
        offreEmplois.setImage(filename);
        OffreEmplois new_offre = offreEmploisRepository.save(offreEmplois);

        // Save new Missions
        for (int i = 0; i < missionsOffres.size(); i++) {
            MissionsOffre new_mission = new MissionsOffre();
            new_mission.setName(missionsOffres.get(i).getName());
            new_mission.setOffreEmplois(new_offre);
            missionsOffreRepository.save(new_mission);
        }

        // Save new competence
        for (int j = 0; j < competenceOffres.size(); j++) {
            CompetenceOffre new_competence = new CompetenceOffre();
            new_competence.setName(competenceOffres.get(j).getName());
            new_competence.setOffreEmplois(new_offre);
            competenceOffreRepository.save(new_competence);
        }
        return new MessageResponse("Offres creer avec success", true);
    }

    @Transactional
    @Override
    public MessageResponse create_withoutImage(OffreEmplois offreEmplois, List<MissionsOffre> missionsOffres,
            List<CompetenceOffre> competenceOffres) {
        // Save new Offres
        offreEmplois.setActivitySectors(offreEmplois.getActivitySectors());
        offreEmplois.setCreated_at(dtf.format(now));
        offreEmplois.setDateline(offreEmplois.getDateline());
        offreEmplois.setDelete(false);
        offreEmplois.setDetail(offreEmplois.getDetail());
        offreEmplois.setEntrepriseAccount(offreEmplois.getEntrepriseAccount());
        offreEmplois.setNbPlace(offreEmplois.getNbPlace());
        offreEmplois.setState(true);
        offreEmplois.setTitle(offreEmplois.getTitle());
        offreEmplois.setTypeContrat(offreEmplois.getTypeContrat());
        offreEmplois.setTypeEmploi(offreEmplois.getTypeEmploi());
        offreEmplois.setVille(offreEmplois.getVille());
        offreEmplois.setPays(offreEmplois.getPays());
        offreEmplois.setImage(null);
        OffreEmplois new_offre = offreEmploisRepository.save(offreEmplois);

        // Save new Missions
        for (int i = 0; i < missionsOffres.size(); i++) {
            MissionsOffre new_mission = new MissionsOffre();
            new_mission.setName(missionsOffres.get(i).getName());
            new_mission.setOffreEmplois(new_offre);
            missionsOffreRepository.save(new_mission);
        }

        // Save new competence
        for (int j = 0; j < competenceOffres.size(); j++) {
            CompetenceOffre new_competence = new CompetenceOffre();
            new_competence.setName(competenceOffres.get(j).getName());
            new_competence.setOffreEmplois(new_offre);
            competenceOffreRepository.save(new_competence);
        }
        return new MessageResponse("Offres creer avec success", true);
    }

    @Transactional
    @Override
    public MessageResponse update_offre(OffreEmplois offreEmplois, MultipartFile image) throws IOException {
        Optional<OffreEmplois> get_offre = offreEmploisRepository.findById(offreEmplois.getOid());
        if (!get_offre.isEmpty()) {
            // Upload image
            int index = image.getOriginalFilename().lastIndexOf('.');
            String extension = image.getOriginalFilename().substring(index + 1);
            String filename = "ajsearch_image_" + offreEmplois.getEntrepriseAccount().getOid() + "_"
                    + "_"
                    + dtf.format(now).toString() + "." + extension;
            byte[] bytes = image.getBytes();
            String insPath = fileDirectry + filename;
            Files.write(Paths.get(insPath), bytes);

            // Update offre
            get_offre.get().setActivitySectors(offreEmplois.getActivitySectors());
            get_offre.get().setDateline(offreEmplois.getDateline());
            get_offre.get().setDelete(false);
            get_offre.get().setDetail(offreEmplois.getDetail());
            get_offre.get().setImage(filename);
            get_offre.get().setNbPlace(offreEmplois.getNbPlace());
            get_offre.get().setTitle(offreEmplois.getTitle());
            get_offre.get().setTypeContrat(offreEmplois.getTypeContrat());
            get_offre.get().setTypeEmploi(offreEmplois.getTypeEmploi());
            get_offre.get().setUpdate_at(dtf.format(now));
            get_offre.get().setVille(offreEmplois.getVille());
            get_offre.get().setPays(offreEmplois.getPays());
            offreEmploisRepository.save(get_offre.get());
            return new MessageResponse("Offres modifier avec success", true);

        } else {
            return new MessageResponse("L'element recherché n'existe pas", false);
        }
    }

    @Transactional
    @Override
    public MessageResponse update_withoutImage(OffreEmplois offreEmplois) {
        Optional<OffreEmplois> get_offre = offreEmploisRepository.findById(offreEmplois.getOid());
        if (!get_offre.isEmpty()) {

            // Update offre
            get_offre.get().setActivitySectors(offreEmplois.getActivitySectors());
            get_offre.get().setDateline(offreEmplois.getDateline());
            // get_offre.get().setDelete(false);
            get_offre.get().setDetail(offreEmplois.getDetail());
            // get_offre.get().setImage(filename);
            get_offre.get().setNbPlace(offreEmplois.getNbPlace());
            get_offre.get().setTitle(offreEmplois.getTitle());
            get_offre.get().setTypeContrat(offreEmplois.getTypeContrat());
            get_offre.get().setTypeEmploi(offreEmplois.getTypeEmploi());
            get_offre.get().setUpdate_at(dtf.format(now));
            get_offre.get().setVille(offreEmplois.getVille());
            get_offre.get().setPays(offreEmplois.getPays());
            offreEmploisRepository.save(get_offre.get());
            return new MessageResponse("Offres modifier avec success", true);

        } else {
            return new MessageResponse("L'element recherché n'existe pas", false);
        }
    }

    @Override
    public Optional<OffreEmplois> find_offre(Long oid_offre) {
        return offreEmploisRepository.findById(oid_offre);
    }

    @Override
    public List<OffreEmplois> list_offre() {
        return offreEmploisRepository.listOffre();
    }

    @Override
    public void getImageOffre(String filename, HttpServletResponse response) {
        // String folderPath = "C:/Users/l.tchiendje/Documents/cv-tech/documents/";
        System.out.println(fileDirectry + filename);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            try (FileInputStream fis = new FileInputStream(fileDirectry + filename)) {
                int len;
                byte[] buf = new byte[1024];
                while ((len = fis.read(buf)) > 0) {
                    bos.write(buf, 0, len);
                }
            }
            bos.close();
            response.flushBuffer();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public List<OffreEmplois> list_similary_offre(ActivitySectors activitySectors, String pays) {
        return offreEmploisRepository.findByActivitySectorsAndPays(activitySectors, pays);
    }

    @Override
    public MessageResponse postuler_offre(PostulerOffre postulerOffre) {
        postulerOffre.setCandidat(postulerOffre.getCandidat());
        postulerOffre.setCreated_at(dtf.format(now));
        postulerOffre.setOffreEmplois(postulerOffre.getOffreEmplois());
        postulerOffreRepository.save(postulerOffre);
        return new MessageResponse("Votre requète a été effectuer avec success", true);
    }

    @Override
    public List<OffreEmplois> list_my_offre(EntrepriseAccount entrepriseAccount) {
        return offreEmploisRepository.listMyOffre(entrepriseAccount);
    }

    @Override
    public MessageResponse supprimer_offre(OffreEmplois offreEmplois) {
        Optional<OffreEmplois> get_offre = offreEmploisRepository.findById(offreEmplois.getOid());
        if (!get_offre.isEmpty()) {
            get_offre.get().setDelete(true);
            get_offre.get().setUpdate_at(dtf.format(now));
            offreEmploisRepository.save(get_offre.get());
            return new MessageResponse("Offres supprimé avec success", true);

        } else {
            return new MessageResponse("L'element recherché n'existe pas", false);
        }
    }

}
