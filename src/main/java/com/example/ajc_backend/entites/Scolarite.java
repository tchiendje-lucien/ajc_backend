package com.example.ajc_backend.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Scolarite {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long oid;
	@Column(nullable=false)
	private Date datedebut;
	private Date datefin;
	@Column(length=100, nullable=false)
	private String domaine;
	@Column(length=15, nullable=false)
	private String niveau;
	@Column(nullable=false)
	private String pays;
	@Column(nullable=false)
	private String ville;
	@ManyToOne
	@JsonProperty(access=Access.WRITE_ONLY)
	private Candidat candidat;
}
