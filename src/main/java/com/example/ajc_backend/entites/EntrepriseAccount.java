package com.example.ajc_backend.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    private String entrepriseId;
    private String password;
    private String re_password;
    private String social_reason;
    private String another_activitySector;
    private String adress;
    private boolean state;
    private boolean isDelete;
    private String logo;
    private String role;
    private String created_at;
    private String updated_at;
    @ManyToOne
    private ActivitySectors activitySectors;
    @ManyToOne
    private Villes villes;
    @JsonIgnore
    @OneToMany(mappedBy = "entrepriseAccount")
    Collection<RespoEntreprise> respoEntreprises;
    @JsonIgnore
    @OneToMany(mappedBy = "entrepriseAccount")
    Collection<OffreEmplois> offreEmplois;
}
