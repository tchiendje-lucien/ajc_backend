package com.example.ajc_backend.services.implementations.entreprises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.dao.EntrepriseAccountRepository;
import com.example.ajc_backend.dao.PaysRepository;
import com.example.ajc_backend.dao.RespoEntrepriseRepository;
import com.example.ajc_backend.dao.VillesRepository;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.Pays;
import com.example.ajc_backend.entites.RespoEntreprise;
import com.example.ajc_backend.entites.Villes;
import com.example.ajc_backend.services.interfaces.entreprises.EntrpriseAccountService;

@Service
public class EntrpriseAccountImplement implements EntrpriseAccountService {

    @Autowired
    EntrepriseAccountRepository entrepriseAccountRepository;
    @Autowired
    RespoEntrepriseRepository respoEntrepriseRepository;
    @Autowired
    PaysRepository paysRepository;
    @Autowired
    VillesRepository villesRepository;

    @Override
    @Transactional
    public MessageResponse create_entreprise(EntrepriseAccount entrepriseAccount,
            List<RespoEntreprise> respoEntreprises, Pays pays, MultipartFile logo) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!entrepriseAccountRepository.findByEntrepriseId(entrepriseAccount.getEntrepriseId()).isEmpty()) {
            return new MessageResponse("Cet identifiant est deja utiliser", false);
        }

        // Sate countries
        pays.setName(pays.getName());
        Pays new_pays = paysRepository.save(pays);

        // Save ville
        Villes villes = new Villes();
        villes.setName(entrepriseAccount.getVilles().getName());
        villes.setPays(new_pays);
        Villes new_ville = villesRepository.save(villes);

        // Upload logo
        int index = logo.getOriginalFilename().lastIndexOf('.');
        String extension = logo.getOriginalFilename().substring(index + 1);
        String filename = "ajsearch_logo_" + entrepriseAccount.getEntrepriseId() + "_" + entrepriseAccount.getName()
                + "_"
                + dtf.format(now).toString() + "." + extension;
        byte[] bytes = logo.getBytes();
        String insPath = "C:/Users/l.tchiendje/Documents/ajc-web-site/logo/" + filename;
        Files.write(Paths.get(insPath), bytes);

        // Save entreprise
        entrepriseAccount.setAdress(entrepriseAccount.getAdress());
        entrepriseAccount.setAnother_activitySector(entrepriseAccount.getAnother_activitySector());
        entrepriseAccount.setActivitySectors(entrepriseAccount.getActivitySectors());
        entrepriseAccount.setCreated_at(dtf.format(now));
        entrepriseAccount.setEntrepriseId(entrepriseAccount.getEntrepriseId());
        entrepriseAccount.setLogo(filename);
        entrepriseAccount.setName(entrepriseAccount.getName());
        entrepriseAccount.setPassword(passwordEncoder.encode(entrepriseAccount.getPassword()));
        entrepriseAccount.setRe_password(null);
        entrepriseAccount.setSocial_reason(entrepriseAccount.getSocial_reason());
        entrepriseAccount.setVilles(new_ville);
        EntrepriseAccount new_entreprise = entrepriseAccountRepository.save(entrepriseAccount);

        // Save Responsable entreprise
        for (int i = 0; i < respoEntreprises.size(); i++) {
            RespoEntreprise new_respo = new RespoEntreprise();
            new_respo.setEmail(respoEntreprises.get(i).getEmail());
            new_respo.setCivility(respoEntreprises.get(i).getCivility());
            new_respo.setCreated_at(dtf.format(now));
            new_respo.setFunction(respoEntreprises.get(i).getFunction());
            new_respo.setName(respoEntreprises.get(i).getName());
            new_respo.setNumFixe(respoEntreprises.get(i).getNumFixe());
            new_respo.setNumPortable(respoEntreprises.get(i).getNumPortable());
            new_respo.setSurname(respoEntreprises.get(i).getSurname());
            new_respo.setEntrepriseAccount(new_entreprise);
            respoEntrepriseRepository.save(new_respo);
        }
        return new MessageResponse("Compte creer avec success", true);
    }

    @Override
    @Transactional
    public MessageResponse create_entreprise_withoutLogo(EntrepriseAccount entrepriseAccount,
            List<RespoEntreprise> respoEntreprises, Pays pays) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!entrepriseAccountRepository.findByEntrepriseId(entrepriseAccount.getEntrepriseId()).isEmpty()) {
            return new MessageResponse("Cet identifiant est deja utiliser", false);
        }

        // Sate countries
        pays.setName(pays.getName());
        Pays new_pays = paysRepository.save(pays);

        // Save ville
        Villes villes = new Villes();
        villes.setName(entrepriseAccount.getVilles().getName());
        villes.setPays(new_pays);
        Villes new_ville = villesRepository.save(villes);

        // Save entreprise
        entrepriseAccount.setAdress(entrepriseAccount.getAdress());
        entrepriseAccount.setAnother_activitySector(entrepriseAccount.getAnother_activitySector());
        entrepriseAccount.setActivitySectors(entrepriseAccount.getActivitySectors());
        entrepriseAccount.setCreated_at(dtf.format(now));
        entrepriseAccount.setUpdated_at(dtf.format(now));
        entrepriseAccount.setEntrepriseId(entrepriseAccount.getEntrepriseId());
        entrepriseAccount.setLogo(null);
        entrepriseAccount.setName(entrepriseAccount.getName());
        entrepriseAccount.setPassword(passwordEncoder.encode(entrepriseAccount.getPassword()));
        entrepriseAccount.setRe_password(null);
        entrepriseAccount.setSocial_reason(entrepriseAccount.getSocial_reason());
        entrepriseAccount.setVilles(new_ville);
        EntrepriseAccount new_entreprise = entrepriseAccountRepository.save(entrepriseAccount);

        // Save Responsable entreprise
        for (int i = 0; i < respoEntreprises.size(); i++) {
            RespoEntreprise new_respo = new RespoEntreprise();
            new_respo.setEmail(respoEntreprises.get(i).getEmail());
            new_respo.setCivility(respoEntreprises.get(i).getCivility());
            new_respo.setCreated_at(dtf.format(now));
            new_respo.setUpdated_at(dtf.format(now));
            new_respo.setFunction(respoEntreprises.get(i).getFunction());
            new_respo.setName(respoEntreprises.get(i).getName());
            new_respo.setNumFixe(respoEntreprises.get(i).getNumFixe());
            new_respo.setNumPortable(respoEntreprises.get(i).getNumPortable());
            new_respo.setSurname(respoEntreprises.get(i).getSurname());
            new_respo.setEntrepriseAccount(new_entreprise);
            respoEntrepriseRepository.save(new_respo);
        }
        return new MessageResponse("Compte creer avec success", true);
    }

    @Override
    @Transactional
    public MessageResponse update_entreprise(EntrepriseAccount entrepriseAccount,
            List<RespoEntreprise> respoEntreprises, Pays pays, MultipartFile logo) throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        Optional<EntrepriseAccount> get_entreprise = entrepriseAccountRepository.findById(entrepriseAccount.getOid());

        if (get_entreprise.isPresent()) {
            if (!entrepriseAccountRepository.findByEntrepriseId(entrepriseAccount.getEntrepriseId()).isEmpty()) {
                return new MessageResponse("Cet identifiant est deja utiliser", false);
            }

            entrepriseAccountRepository.deleteByVilles(get_entreprise.get().getVilles());
            // Update ville
            Villes villes = new Villes();
            villes.setName(entrepriseAccount.getVilles().getName());
            villes.setPays(pays);
            Villes new_ville = villesRepository.save(villes);

            // Upload logo
            int index = logo.getOriginalFilename().lastIndexOf('.');
            String extension = logo.getOriginalFilename().substring(index + 1);
            String filename = "ajsearch_logo_" + entrepriseAccount.getEntrepriseId() + "_"
                    + entrepriseAccount.getName()
                    + "_"
                    + dtf.format(now).toString() + "." + extension;
            byte[] bytes = logo.getBytes();
            String insPath = "C:/Users/l.tchiendje/Documents/ajc-web-site/logo/" + filename;
            Files.write(Paths.get(insPath), bytes);

            // Update entreprise
            get_entreprise.get().setAdress(entrepriseAccount.getAdress());
            get_entreprise.get().setAnother_activitySector(entrepriseAccount.getAnother_activitySector());
            get_entreprise.get().setActivitySectors(entrepriseAccount.getActivitySectors());
            get_entreprise.get().setCreated_at(dtf.format(now));
            get_entreprise.get().setUpdated_at(dtf.format(now));
            get_entreprise.get().setEntrepriseId(entrepriseAccount.getEntrepriseId());
            get_entreprise.get().setLogo(filename);
            get_entreprise.get().setName(entrepriseAccount.getName());
            get_entreprise.get().setPassword(entrepriseAccount.getPassword());
            get_entreprise.get().setRe_password(null);
            get_entreprise.get().setSocial_reason(entrepriseAccount.getSocial_reason());
            entrepriseAccount.setVilles(new_ville);
            entrepriseAccountRepository.save(get_entreprise.get());

            // Update Responsable entreprise
            respoEntrepriseRepository.deleteByEntrepriseAccount(entrepriseAccount);
            for (int i = 0; i < respoEntreprises.size(); i++) {
                RespoEntreprise new_respo = new RespoEntreprise();
                new_respo.setEmail(respoEntreprises.get(i).getEmail());
                new_respo.setCivility(respoEntreprises.get(i).getCivility());
                new_respo.setCreated_at(dtf.format(now));
                new_respo.setUpdated_at(dtf.format(now));
                new_respo.setFunction(respoEntreprises.get(i).getFunction());
                new_respo.setName(respoEntreprises.get(i).getName());
                new_respo.setNumFixe(respoEntreprises.get(i).getNumFixe());
                new_respo.setNumPortable(respoEntreprises.get(i).getNumPortable());
                new_respo.setSurname(respoEntreprises.get(i).getSurname());
                new_respo.setEntrepriseAccount(entrepriseAccount);
                respoEntrepriseRepository.save(new_respo);
            }
            return new MessageResponse("Compte Modifier avec success", true);

        } else {
            return new MessageResponse("Cet entreprise n'existe pas", false);
        }
    }

    @Override
    @Transactional
    public MessageResponse update_entreprise_withoutLogo(EntrepriseAccount entrepriseAccount,
            List<RespoEntreprise> respoEntreprises, Pays pays) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        Optional<EntrepriseAccount> get_entreprise = entrepriseAccountRepository.findById(entrepriseAccount.getOid());

        if (get_entreprise.isPresent()) {
            if (!entrepriseAccountRepository.findByEntrepriseId(entrepriseAccount.getEntrepriseId()).isEmpty()) {
                return new MessageResponse("Cet identifiant est deja utiliser", false);
            }

            entrepriseAccountRepository.deleteByVilles(get_entreprise.get().getVilles());
            // Update ville
            Villes villes = new Villes();
            villes.setName(entrepriseAccount.getVilles().getName());
            villes.setPays(pays);
            Villes new_ville = villesRepository.save(villes);

            // Update entreprise
            get_entreprise.get().setAdress(entrepriseAccount.getAdress());
            get_entreprise.get().setAnother_activitySector(entrepriseAccount.getAnother_activitySector());
            get_entreprise.get().setActivitySectors(entrepriseAccount.getActivitySectors());
            get_entreprise.get().setCreated_at(dtf.format(now));
            get_entreprise.get().setUpdated_at(dtf.format(now));
            get_entreprise.get().setEntrepriseId(entrepriseAccount.getEntrepriseId());
            get_entreprise.get().setLogo(entrepriseAccount.getLogo());
            get_entreprise.get().setName(entrepriseAccount.getName());
            get_entreprise.get().setPassword(entrepriseAccount.getPassword());
            get_entreprise.get().setRe_password(null);
            get_entreprise.get().setSocial_reason(entrepriseAccount.getSocial_reason());
            entrepriseAccount.setVilles(new_ville);
            entrepriseAccountRepository.save(get_entreprise.get());

            // Update Responsable entreprise
            respoEntrepriseRepository.deleteByEntrepriseAccount(entrepriseAccount);
            for (int i = 0; i < respoEntreprises.size(); i++) {
                RespoEntreprise new_respo = new RespoEntreprise();
                new_respo.setEmail(respoEntreprises.get(i).getEmail());
                new_respo.setCivility(respoEntreprises.get(i).getCivility());
                new_respo.setCreated_at(dtf.format(now));
                new_respo.setUpdated_at(dtf.format(now));
                new_respo.setFunction(respoEntreprises.get(i).getFunction());
                new_respo.setName(respoEntreprises.get(i).getName());
                new_respo.setNumFixe(respoEntreprises.get(i).getNumFixe());
                new_respo.setNumPortable(respoEntreprises.get(i).getNumPortable());
                new_respo.setSurname(respoEntreprises.get(i).getSurname());
                new_respo.setEntrepriseAccount(entrepriseAccount);
                respoEntrepriseRepository.save(new_respo);
            }
            return new MessageResponse("Compte Modifier avec success", true);

        } else {
            return new MessageResponse("Cette entreprise n'existe pas", false);
        }
    }

    @Override
    public List<EntrepriseAccount> list_entrepriseAccounts() {
        return entrepriseAccountRepository.findAll();
    }

    @Override
    public MessageResponse change_password(EntrepriseAccount entrepriseAccount) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<EntrepriseAccount> get_entreprise = entrepriseAccountRepository
                .findByEntrepriseId(entrepriseAccount.getEntrepriseId());
        if (get_entreprise.isPresent()) {
            if (passwordEncoder.matches(entrepriseAccount.getPassword(), get_entreprise.get().getPassword())) {
                get_entreprise.get().setPassword(passwordEncoder.encode(entrepriseAccount.getRe_password()));
                entrepriseAccountRepository.save(get_entreprise.get());
                return new MessageResponse("Mot de passe mit a jour avec success", true);
            } else {
                return new MessageResponse("Mot de passe different", false);
            }
        } else {
            return new MessageResponse("Cet utilisateur n'existe pas", false);
        }
    }

}
