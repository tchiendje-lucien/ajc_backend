package com.example.ajc_backend.dao;

import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.Villes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseAccountRepository extends JpaRepository<EntrepriseAccount, Long> {
    Optional<EntrepriseAccount> findByEntrepriseId(String entrepriseId);

    public void deleteByVilles(Villes villes);
}