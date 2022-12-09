package com.example.ajc_backend.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.CrossOrigin;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.io.Serializable;

@CrossOrigin("*")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private String identifiant;
    private String role;
    // @JsonProperty(access=Access.WRITE_ONLY)
    private String password;
    private String re_password;
    @OneToOne(mappedBy = "users")
    private EntrepriseAccount entrepriseAccount;
    @OneToOne(mappedBy = "users")
    private Candidat candidat;
}
