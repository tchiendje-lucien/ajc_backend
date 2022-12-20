package com.example.ajc_backend.services.interfaces.candidat;

import java.util.List;

import com.example.ajc_backend.ExceptionMessage;
import com.example.ajc_backend.entites.Candidat;
import com.example.ajc_backend.entites.Users;

public interface CandidatService {
	
	Candidat add_candidat(Candidat candidat) throws ExceptionMessage;
	Candidat edit_candidat(Candidat candidat);
	void dell_candidat(Long oid);
	List<Candidat> list_candidat();
	Candidat list_one_candidat(Long oid);
	Candidat connexion(String user, String pwd);
	Candidat loadByUserName(String username);
	Users tokenUser(String token);

}
