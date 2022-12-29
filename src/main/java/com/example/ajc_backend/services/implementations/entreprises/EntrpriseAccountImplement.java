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
import com.example.ajc_backend.dao.RespoEntrepriseRepository;
import com.example.ajc_backend.dao.UsersRepository;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.RespoEntreprise;
import com.example.ajc_backend.entites.Users;
import com.example.ajc_backend.services.interfaces.entreprises.EntrpriseAccountService;

@Service
public class EntrpriseAccountImplement implements EntrpriseAccountService {

    @Autowired
    EntrepriseAccountRepository entrepriseAccountRepository;
    @Autowired
    RespoEntrepriseRepository respoEntrepriseRepository;
    @Autowired
    UsersRepository usersRepository;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @Override
    @Transactional
    public MessageResponse create_entreprise(EntrepriseAccount entrepriseAccount,
            List<RespoEntreprise> respoEntreprises, Users users, MultipartFile logo) throws IOException {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (usersRepository.findByIdentifiant(users.getIdentifiant()).isPresent()) {
            return new MessageResponse("Cet identifiant est deja utiliser", false);
        }

        // Save users
        users.setIdentifiant(users.getIdentifiant());
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRole(users.getRole());
        Users new_user = usersRepository.save(users);

        // Upload logo
        int index = logo.getOriginalFilename().lastIndexOf('.');
        String extension = logo.getOriginalFilename().substring(index + 1);
        String filename = "ajsearch_logo_" + new_user.getOid() + "_" + entrepriseAccount.getName()
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
        entrepriseAccount.setLogo(filename);
        entrepriseAccount.setName(entrepriseAccount.getName());
        entrepriseAccount.setSocial_reason(entrepriseAccount.getSocial_reason());
        entrepriseAccount.setVille(entrepriseAccount.getVille());
        entrepriseAccount.setPays(entrepriseAccount.getPays());
        entrepriseAccount.setUsers(new_user);
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
            List<RespoEntreprise> respoEntreprises, Users users) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (usersRepository.findByIdentifiant(users.getIdentifiant()).isPresent()) {
            return new MessageResponse("Cet identifiant est deja utiliser", false);
        }

        // Save users
        users.setIdentifiant(users.getIdentifiant());
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRole(users.getRole());
        Users new_user = usersRepository.save(users);

        // Save entreprise
        entrepriseAccount.setAdress(entrepriseAccount.getAdress());
        entrepriseAccount.setAnother_activitySector(entrepriseAccount.getAnother_activitySector());
        entrepriseAccount.setActivitySectors(entrepriseAccount.getActivitySectors());
        entrepriseAccount.setCreated_at(dtf.format(now));
        entrepriseAccount.setUpdated_at(dtf.format(now));
        entrepriseAccount.setLogo(null);
        entrepriseAccount.setName(entrepriseAccount.getName());
        entrepriseAccount.setSocial_reason(entrepriseAccount.getSocial_reason());
        entrepriseAccount.setVille(entrepriseAccount.getVille());
        entrepriseAccount.setPays(entrepriseAccount.getPays());
        entrepriseAccount.setUsers(new_user);
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
        return new MessageResponse("Les informations de votre entreprise ont été Modifié avec success", true);
    }

    @Override
    @Transactional
    public MessageResponse update_entreprise(EntrepriseAccount entrepriseAccount, MultipartFile logo)
            throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();

        // Update entreprise
        Optional<EntrepriseAccount> get_entreprise = entrepriseAccountRepository
                .findById(entrepriseAccount.getOid());
        if (get_entreprise.isPresent()) {
            // Upload logo
            int index = logo.getOriginalFilename().lastIndexOf('.');
            String extension = logo.getOriginalFilename().substring(index + 1);
            String filename = "ajsearch_logo_" + entrepriseAccount.getOid() + "_"
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
            get_entreprise.get().setLogo(filename);
            get_entreprise.get().setName(entrepriseAccount.getName());
            get_entreprise.get().setSocial_reason(entrepriseAccount.getSocial_reason());
            get_entreprise.get().setVille(entrepriseAccount.getVille());
            get_entreprise.get().setPays(entrepriseAccount.getPays());
            entrepriseAccountRepository.save(get_entreprise.get());

            return new MessageResponse("Les informations de votre entreprise ont été Modifié avec success", true);

        } else {
            return new MessageResponse("L'entreprise recherchée n'existe pas", false);
        }
    }

    @Override
    @Transactional
    public MessageResponse update_entreprise_withoutLogo(EntrepriseAccount entrepriseAccount) {


        // Update entreprise
        Optional<EntrepriseAccount> get_entreprise = entrepriseAccountRepository
                .findById(entrepriseAccount.getOid());
        if (get_entreprise.isPresent()) {
            get_entreprise.get().setAdress(entrepriseAccount.getAdress());
            get_entreprise.get().setAnother_activitySector(entrepriseAccount.getAnother_activitySector());
            get_entreprise.get().setActivitySectors(entrepriseAccount.getActivitySectors());
            get_entreprise.get().setCreated_at(dtf.format(now));
            get_entreprise.get().setUpdated_at(dtf.format(now));
            // get_entreprise.get().setLogo(entrepriseAccount.getLogo());
            get_entreprise.get().setName(entrepriseAccount.getName());
            get_entreprise.get().setSocial_reason(entrepriseAccount.getSocial_reason());
            get_entreprise.get().setVille(entrepriseAccount.getVille());
            get_entreprise.get().setPays(entrepriseAccount.getPays());
            entrepriseAccountRepository.save(get_entreprise.get());

            return new MessageResponse("Les informations de votre entreprise ont été Modifié avec success", true);
        } else {
            return new MessageResponse("L'entreprise recherchée n'existe pas", false);
        }

    }

    @Override
    public List<EntrepriseAccount> list_entrepriseAccounts() {
        return entrepriseAccountRepository.findAll();
    }

}
