package com.example.ajc_backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ajc_backend.entites.Chopper;
import com.example.ajc_backend.entites.EntrepriseAccount;

@RepositoryRestResource
public interface ChopperRepository extends JpaRepository<Chopper, Long> {
    List<Chopper> findByEntrepriseAccount(EntrepriseAccount entrepriseAccount);

    @Query(value = "select c from Chopper c where c.entrepriseAccount = :entrepriseAccount and c.isDelete = false ")
    List<Chopper> listMyChopperProfile(@Param("entrepriseAccount") EntrepriseAccount entrepriseAccount);
}
