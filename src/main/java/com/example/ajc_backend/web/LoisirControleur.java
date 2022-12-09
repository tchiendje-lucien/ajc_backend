package com.example.ajc_backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.entites.Loisir;
import com.example.ajc_backend.services.interfaces.candidat.LosirService;

@RestController
@CrossOrigin("*")
public class LoisirControleur {


	@Autowired
	LosirService losirService;
	
	@PostMapping(value="/add_loisir")
	public Loisir add_loisir(@RequestBody Loisir loisir) {
		// TODO Auto-generated method stub
		return losirService.add_loisir(loisir);
	}
	
	@PostMapping(value="/edit_loisir")
	public Loisir edit_loisir(@RequestBody Loisir loisir) {
		// TODO Auto-generated method stub		
		return losirService.edit_loisir(loisir);
	}
	
	@PostMapping(value="/dell_loisir")
	public void dell_loisir(@RequestBody Loisir loisir) {
		// TODO Auto-generated method stub		
		losirService.dell_loisir(loisir.getOid());
	}
	
	@PostMapping(value="/list_loisir")
	public List<Loisir> list_loisir() {
		// TODO Auto-generated method stub		
		return losirService.list_loisir();
	}
	
}
