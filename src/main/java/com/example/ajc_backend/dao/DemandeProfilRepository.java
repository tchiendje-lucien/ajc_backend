
package com.example.ajc_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ajc_backend.entites.DemandeProfil;
import com.example.ajc_backend.entites.EntrepriseAccount;

@RepositoryRestResource
public interface DemandeProfilRepository extends JpaRepository<DemandeProfil, Long> {
    List<DemandeProfil> findByEntrepriseAccount(EntrepriseAccount entrepriseAccount);
}