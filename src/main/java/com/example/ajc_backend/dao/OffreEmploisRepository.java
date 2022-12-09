package com.example.ajc_backend.dao;

import com.example.ajc_backend.entites.ActivitySectors;
import com.example.ajc_backend.entites.EntrepriseAccount;
import com.example.ajc_backend.entites.OffreEmplois;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OffreEmploisRepository extends JpaRepository<OffreEmplois, Long> {
    List<OffreEmplois> findByEntrepriseAccount(EntrepriseAccount entrepriseAccount);

    List<OffreEmplois> findByActivitySectorsAndPays(ActivitySectors activitySectors, String pays);

    @Query(value = "select o from OffreEmplois o where o.isDelete!=true")
    List<OffreEmplois> listOffre();

    @Query(value = "select o from OffreEmplois o where o.entrepriseAccount=:entrepriseAccount and o.isDelete!=true ")
    List<OffreEmplois> listMyOffre(@Param("entrepriseAccount") EntrepriseAccount entrepriseAccount);
}