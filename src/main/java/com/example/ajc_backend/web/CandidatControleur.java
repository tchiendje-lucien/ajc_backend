package com.example.ajc_backend.web;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.entites.Candidat;
import com.example.ajc_backend.services.interfaces.candidat.CandidatService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@CrossOrigin("*")
public class CandidatControleur {
	
	@Autowired
	CandidatService candidatService;
	
	
	//////  candidat
	@PostMapping(value = "/add_candidat")
	public Candidat add_candidat(@RequestBody Candidat candidat ) {	
		return candidatService.add_candidat(candidat);
	}
	@PutMapping(value = "/edit_candidat")
	public Candidat edit_candidat(@RequestBody Candidat candidat ) {	
		return candidatService.edit_candidat(candidat);
	}
	@DeleteMapping(value = "/dell_candidat")
	public void dell_candidat(@RequestBody Long oid) {	
		candidatService.dell_candidat(oid);
	}
	@GetMapping(value = "/list_candidat")
	public List<Candidat> list_candidat() {	
		return candidatService.list_candidat();
	}
	@PostMapping(value = "/connexion_candidat")
	public Candidat connexion_candidat(@RequestBody User user) {	
	
		Candidat candidat = candidatService.connexion(user.getUser(),user.getPwd());
	
		return candidat;
	}
	
	
	
	/////// experience
	

}


@Data @NoArgsConstructor @AllArgsConstructor
class User {
	
	 private String user;
	 private String pwd;

}
