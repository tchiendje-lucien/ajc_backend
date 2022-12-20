package com.example.ajc_backend.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.io.Serializable;
import java.util.Collection;

@CrossOrigin("*")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntrepriseAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private String name;
    private String social_reason;
    private String another_activitySector;
    private String adress;
    private String pays;
    private String ville;
    private boolean state;
    private boolean isDelete;
    private String logo;
    private String created_at;
    private String updated_at;
    @ManyToOne
    private ActivitySectors activitySectors;
    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToOne(cascade = CascadeType.ALL)
    private Users users;
    @OneToMany(mappedBy = "entrepriseAccount")
    Collection<RespoEntreprise> respoEntreprises;
    @JsonIgnore
    @OneToMany(mappedBy = "entrepriseAccount")
    Collection<OffreEmplois> offreEmplois;
    @JsonIgnore
    @OneToMany(mappedBy = "entrepriseAccount")
    Collection<DemandeProfil> demandeProfils;
    @JsonIgnore
    @OneToMany(mappedBy = "entrepriseAccount")
    Collection<Chopper> choppers;
}
