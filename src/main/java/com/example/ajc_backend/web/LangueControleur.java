package com.example.ajc_backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.entites.Langue;
import com.example.ajc_backend.services.interfaces.candidat.LangueService;


@RestController
@CrossOrigin("*")
public class LangueControleur {

	
	@Autowired
	LangueService langueService;
	
	@PostMapping(value="/add_langue")
	public Langue add_langue(@RequestBody Langue langue) {
		// TODO Auto-generated method stub
		return langueService.add_langue(langue);
	}
	
	@PostMapping(value="/edit_langue")
	public Langue edit_langue(@RequestBody Langue langue) {
		// TODO Auto-generated method stub		
		return langueService.edit_langue(langue);
	}
	
	@PostMapping(value="/dell_langue")
	public void dell_langue(@RequestBody Langue langue) {
		// TODO Auto-generated method stub		
		langueService.dell_langue(langue.getOid());
	}
	
	@PostMapping(value="/list_langue")
	public List<Langue> list_langue() {
		// TODO Auto-generated method stub		
		return langueService.list_langue();
	}
	
}
