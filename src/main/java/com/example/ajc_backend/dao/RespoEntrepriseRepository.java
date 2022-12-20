package com.example.ajc_backend.dao;

import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.RespoEntreprise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RespoEntrepriseRepository extends JpaRepository<RespoEntreprise, Long> {
    public void deleteByEntrepriseAccount(EntrepriseAccount entrepriseAccount);
    List<RespoEntreprise> findByEntrepriseAccount(EntrepriseAccount entrepriseAccount);
}