package com.example.ajc_backend.web;




import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.ExceptionMessage;
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
	public Candidat add_candidat(@RequestBody Candidat candidat ) throws ExceptionMessage{	
		
		Candidat candidat2 = null;
		try {
			candidat2 = candidatService.add_candidat(candidat);
		} catch (ExceptionMessage e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
		return candidat2;
	}
	
	@PostMapping(value = "/edit_candidat")
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
	
	@GetMapping(value = "/detail_one_candidat/{oidcandidat}")
	public Candidat detail_one_candidat(@PathVariable("oidcandidat") Long oidcandidat) {	
		return candidatService.list_one_candidat(oidcandidat);
	}
	
	
	@GetMapping(value = "/load_candidat")
	public Candidat load_candidat(@RequestHeader(name = "Authorization") String token) {	
		System.out.println("Token = "+token);
		String user=candidatService.tokenUser(token).getIdentifiant();
		System.out.println("User = "+user);
		return candidatService.loadByUserName(user);
	}
	
	@PostMapping(value = "/connexion_candidat")
	public Candidat connexion_candidat(@RequestBody User user) {	
	
		Candidat candidat = candidatService.connexion(user.getUser(),user.getPwd());
	
		return candidat;
	}
	
	
	@GetMapping(value = "/list_pays")
    public List<String> getCountriesListInAlphabetical() {
        List<String> crunchifyList = new ArrayList<String>();
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            crunchifyList.add(obj.getDisplayCountry());
        }
        Collections.sort(crunchifyList);
        // ListIterator<String> crunchifyListIterator = crunchifyList.listIterator();
        // while (crunchifyListIterator.hasNext()) {
        //     System.out.println(crunchifyListIterator.next());
        // }
        return crunchifyList;
    }
	

}


@Data @NoArgsConstructor @AllArgsConstructor
class User {
	
	 private String user;
	 private String pwd;

}


