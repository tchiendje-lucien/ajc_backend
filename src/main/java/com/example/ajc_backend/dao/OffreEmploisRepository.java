package com.example.ajc_backend.dao;

import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.OffreEmplois;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreEmploisRepository extends JpaRepository<OffreEmplois, Long> {
    List<OffreEmplois> findByEntrepriseAccount(EntrepriseAccount entrepriseAccount);
}