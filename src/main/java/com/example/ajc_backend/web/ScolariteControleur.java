package com.example.ajc_backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.entites.Scolarite;
import com.example.ajc_backend.services.interfaces.candidat.ScolariteService;

@RestController
@CrossOrigin("*")
public class ScolariteControleur {

	
	@Autowired
	ScolariteService scolariteService;
	
	@PostMapping(value="/add_scolarite")
	public Scolarite add_competence(@RequestBody Scolarite scolarite) {
		// TODO Auto-generated method stub
		return scolariteService.add_scolarite(scolarite);
	}
	
	@PostMapping(value="/edit_scolarite")
	public Scolarite edit_competence(@RequestBody Scolarite scolarite) {
		// TODO Auto-generated method stub		
		return scolariteService.edit_scolarite(scolarite);
	}
	
	@PostMapping(value="/dell_scolarite")
	public void dell_competence(@RequestBody Scolarite scolarite) {
		// TODO Auto-generated method stub		
		scolariteService.dell_scolarite(scolarite.getOid());;
	}
	
	@PostMapping(value="/list_scolarite")
	public List<Scolarite> list_competence() {
		// TODO Auto-generated method stub		
		return scolariteService.list_scolarite();
	}
	
}
