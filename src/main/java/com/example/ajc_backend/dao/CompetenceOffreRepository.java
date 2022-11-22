package com.example.ajc_backend.dao;

import com.example.ajc_backend.entites.CompetenceOffre;
import com.example.ajc_backend.entites.OffreEmplois;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceOffreRepository extends JpaRepository<CompetenceOffre, Long> {
    List<CompetenceOffre> findByOffreEmplois(OffreEmplois offreEmplois);
}