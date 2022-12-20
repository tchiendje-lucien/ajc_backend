package com.example.ajc_backend.entites;

import java.io.Serializable;

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
public class Competence implements Serializable{
    
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long oid;
	@Column(length=100)
	private String domaine;
	@Column(length=500)
	private String detail;
	@ManyToOne
	@JsonProperty(access=Access.WRITE_ONLY)
	private Candidat candidat;
}
