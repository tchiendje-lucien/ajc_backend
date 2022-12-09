package com.example.ajc_backend.services.implementations.entreprises;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.dao.DemandeProfilRepository;
import com.example.ajc_backend.entites.DemandeProfil;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.services.interfaces.entreprises.DemandeProfilService;

@Service
public class DemandeProfilImplement implements DemandeProfilService {

    @Autowired
    DemandeProfilRepository demandeProfilRepository;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
    LocalDateTime now = LocalDateTime.now();

    @Override
    public MessageResponse create_ask_profile(DemandeProfil demandeProfil) {
        demandeProfil.setCiviliy(demandeProfil.getCiviliy());
        demandeProfil.setCompetence(demandeProfil.getCompetence());
        demandeProfil.setCreated_at(dtf.format(now));
        demandeProfil.setDateline(demandeProfil.getDateline());
        demandeProfil.setDescription(demandeProfil.getDescription());
        demandeProfil.setDomaineFormaton(demandeProfil.getDomaineFormaton());
        demandeProfil.setEntrepriseAccount(demandeProfil.getEntrepriseAccount());
        demandeProfil.setExperience(demandeProfil.getExperience());
        demandeProfil.setHoraireTravail(demandeProfil.getHoraireTravail());
        demandeProfil.setLieuTravail(demandeProfil.getLieuTravail());
        demandeProfil.setNbProfil(demandeProfil.getNbProfil());
        demandeProfil.setNivScolaire(demandeProfil.getNivScolaire());
        demandeProfil.setPoste(demandeProfil.getPoste());
        demandeProfil.setRecrutement(demandeProfil.getRecrutement());
        demandeProfil.setStatutMatrimonial(demandeProfil.getStatutMatrimonial());
        demandeProfil.setTypeContrat(demandeProfil.getTypeContrat());
        demandeProfilRepository.save(demandeProfil);

        return new MessageResponse("Votre demande a été envoyé", true);
    }

    @Override
    public MessageResponse update_ask_profile(DemandeProfil demandeProfil) {
        Optional<DemandeProfil> get_demandeProfile = demandeProfilRepository.findById(demandeProfil.getOid());
        if (get_demandeProfile.isPresent()) {
            get_demandeProfile.get().setCiviliy(demandeProfil.getCiviliy());
            get_demandeProfile.get().setCompetence(demandeProfil.getCompetence());
            get_demandeProfile.get().setUpdated_at(dtf.format(now));
            get_demandeProfile.get().setDateline(demandeProfil.getDateline());
            get_demandeProfile.get().setDescription(demandeProfil.getDescription());
            get_demandeProfile.get().setDomaineFormaton(demandeProfil.getDomaineFormaton());
            get_demandeProfile.get().setEntrepriseAccount(demandeProfil.getEntrepriseAccount());
            get_demandeProfile.get().setExperience(demandeProfil.getExperience());
            get_demandeProfile.get().setHoraireTravail(demandeProfil.getHoraireTravail());
            get_demandeProfile.get().setLieuTravail(demandeProfil.getLieuTravail());
            get_demandeProfile.get().setNbProfil(demandeProfil.getNbProfil());
            get_demandeProfile.get().setNivScolaire(demandeProfil.getNivScolaire());
            get_demandeProfile.get().setPoste(demandeProfil.getPoste());
            get_demandeProfile.get().setRecrutement(demandeProfil.getRecrutement());
            get_demandeProfile.get().setStatutMatrimonial(demandeProfil.getStatutMatrimonial());
            get_demandeProfile.get().setTypeContrat(demandeProfil.getTypeContrat());
            demandeProfilRepository.save(get_demandeProfile.get());

            return new MessageResponse("Votre demande a été modifiée", true);
        } else {
            return new MessageResponse("Désolé mais le profil recherché n'existe pas", false);
        }
    }

    @Override
    public MessageResponse delete_ask_profile(DemandeProfil demandeProfil) {
        Optional<DemandeProfil> get_demandeProfile = demandeProfilRepository.findById(demandeProfil.getOid());
        if (get_demandeProfile.isPresent()) {
            get_demandeProfile.get().setCancel(true);
            get_demandeProfile.get().setUpdated_at(dtf.format(now));
            demandeProfilRepository.save(get_demandeProfile.get());

            return new MessageResponse("Votre demande a été Annuler", true);
        } else {
            return new MessageResponse("Désolé mais le profil recherché n'existe pas", false);
        }
    }

    @Override
    public List<DemandeProfil> list_ask_profile(EntrepriseAccount entrepriseAccount) {
        return demandeProfilRepository.findByEntrepriseAccount(entrepriseAccount);
    }

    @Override
    public Optional<DemandeProfil> edit_ask_profile(Long oid_AkProfile) {
        return demandeProfilRepository.findById(oid_AkProfile);
    }

}
