package com.example.ajc_backend.entites;

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
public class Langue {
   
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long oid;
	@Column(length=20, nullable=false)
	private String langue;
	@Column(length=10, nullable=false)
	private String niveau;
	@Column(nullable=false)
	private String type;
	@ManyToOne
	@JsonProperty(access=Access.WRITE_ONLY)
	private Candidat candidat;
}
