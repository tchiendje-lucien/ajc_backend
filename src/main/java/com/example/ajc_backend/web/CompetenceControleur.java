package com.example.ajc_backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.entites.Candidat;
import com.example.ajc_backend.entites.Competence;
import com.example.ajc_backend.services.interfaces.candidat.CompetenceService;

@RestController
@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:4200")
public class CompetenceControleur {

	@Autowired
	CompetenceService competenceService;
	
	@PostMapping(value="/add_competence")
	public Competence add_competence(@RequestBody Competence competence) {
		// TODO Auto-generated method stub
		System.out.println(" Detail = "+competence.getDetail());
		return competenceService.add_competence(competence);
	}
	
	@PostMapping(value="/edit_competence")
	public Competence edit_competence(@RequestBody Competence competence) {
		// TODO Auto-generated method stub
		System.out.println(" Detail = "+competence.getDetail());
		return competenceService.edit_competence(competence);
	}
	@PostMapping(value="/dell_competence")
	public void dell_competence(@RequestBody Competence competence) {
		// TODO Auto-generated method stub
		competenceService.dell_competence(competence.getOid());
	}

	
}
