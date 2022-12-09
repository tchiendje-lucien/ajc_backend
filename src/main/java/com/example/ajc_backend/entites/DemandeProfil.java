package com.example.ajc_backend.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@CrossOrigin("*")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DemandeProfil implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private String poste;
    private String experience;
    private String domaineFormaton;
    private String dateline;
    private String nivScolaire;
    private String competence;//
    private String civiliy;
    private String statutMatrimonial;
    private Integer nbProfil;
    private Integer duree;//
    private boolean isCancel;
    private boolean status;
    private String typeContrat;
    private String horaireTravail;
    private String recrutement;
    private String lieuTravail;
    private String description;
    private String created_at;
    private String updated_at;
    @ManyToOne
    private EntrepriseAccount entrepriseAccount;

}
