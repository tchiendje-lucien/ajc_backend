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
public class Villes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private String name;
    @ManyToOne
    private Pays pays;
    @JsonIgnore
    @OneToMany(mappedBy = "villes")
    Collection<EntrepriseAccount> entrepriseAccounts;
    @JsonIgnore
    @OneToMany(mappedBy = "villes")
    Collection<OffreEmplois> offreEmplois;

}
