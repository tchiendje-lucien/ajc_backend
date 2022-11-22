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
import javax.persistence.ManyToOne;

import java.io.Serializable;

@CrossOrigin("*")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespoEntreprise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private String name;
    private String surname;
    private String civility;
    private String email;
    private String numPortable;
    private String numFixe;
    private String function;
    private String created_at;
    private String updated_at;
    @ManyToOne
    private EntrepriseAccount entrepriseAccount;
    
}
