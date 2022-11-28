package com.example.ajc_backend.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@CrossOrigin("*")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OffreEmplois implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private String title;
    private String detail;
    private int nbPlace;
    private boolean state;
    private boolean isDelete;
    private String dateline;
    private String pays;
    private String ville;
    private String typeEmploi;
    private String image;
    private String typeContrat;
    private String role;
    private String created_at;
    private String update_at;
    @ManyToOne
    private ActivitySectors activitySectors;
    @ManyToOne
    private EntrepriseAccount entrepriseAccount;
    @JsonIgnore
    @OneToMany(mappedBy = "offreEmplois")
    Collection<CompetenceOffre> competenceOffres;
    @JsonIgnore
    @OneToMany(mappedBy = "offreEmplois")
    Collection<MissionsOffre> missionsOffres;
}