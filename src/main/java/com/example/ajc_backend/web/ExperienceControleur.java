package com.example.ajc_backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.entites.Experiencepro;
import com.example.ajc_backend.services.interfaces.candidat.ExperienceService;


@RestController
@CrossOrigin("*")
public class ExperienceControleur {

	
	@Autowired
	ExperienceService experienceService;
	
	@PostMapping(value="/add_experience")
	public Experiencepro add_experience(@RequestBody Experiencepro experiencepro) {
		// TODO Auto-generated method stub
		return experienceService.add_experience(experiencepro);
	}
	
	@PostMapping(value="/edit_experience")
	public Experiencepro edit_competence(@RequestBody Experiencepro experiencepro) {
		// TODO Auto-generated method stub		
		return experienceService.edit_experience(experiencepro);
	}
	
	@PostMapping(value="/dell_experience")
	public void dell_competence(@RequestBody Experiencepro experiencepro) {
		// TODO Auto-generated method stub		
		experienceService.dell_experience(experiencepro.getOid());
	}
	
	@PostMapping(value="/list_experience")
	public List<Experiencepro> list_competence() {
		// TODO Auto-generated method stub		
		return experienceService.list_experience();
	}
	
}
