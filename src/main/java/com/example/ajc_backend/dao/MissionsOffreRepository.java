package com.example.ajc_backend.dao;

import com.example.ajc_backend.entites.MissionsOffre;
import com.example.ajc_backend.entites.OffreEmplois;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionsOffreRepository extends JpaRepository<MissionsOffre, Long> {
    List<MissionsOffre> findByOffreEmplois(OffreEmplois offreEmplois);
}