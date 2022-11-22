package com.example.ajc_backend.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.io.Serializable;

@CrossOrigin("*")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompetenceOffre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private String name;
    @ManyToOne
    private OffreEmplois offreEmplois;
}