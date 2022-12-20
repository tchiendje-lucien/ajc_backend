package com.example.ajc_backend.entites;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Candidat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long oid;
	@Column(length = 8, nullable = false)
	private String civilite;
	@Column(length = 150, nullable = false)
	private String nom;
	@Column(length = 150)
	private String prenom;
	@Column(nullable = false)
	private String email;
	private String password;
	private Date datenaissance;
	private String lieumaissance;
	private String pays;
	private String ville;
	private String telephone1;
	private String telephone2;
	private String role;
	@OneToOne(cascade = CascadeType.ALL)
	private Users users;
	@OneToMany(mappedBy = "candidat")
	private Collection<Competence> competence;
	@OneToMany(mappedBy = "candidat")
	private Collection<Experiencepro> experience;
	@OneToMany(mappedBy = "candidat")
	private Collection<Langue> langue;
	@OneToMany(mappedBy = "candidat")
	private Collection<Loisir> loisir;
	@OneToMany(mappedBy = "candidat")
	private Collection<Scolarite> scolarite;
	@JsonIgnore
	@OneToMany(mappedBy = "candidat")
	private Collection<PostulerOffre> postulerOffres;
	@JsonIgnore
	@OneToMany(mappedBy = "candidat")
	private Collection<Chopper> choppers;
}
