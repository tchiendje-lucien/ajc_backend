package com.example.ajc_backend.services.interfaces.candidat;

import java.util.List;


import com.example.ajc_backend.entites.Competence;

public interface CompetenceService {

	Competence add_competence(Competence competence);
	Competence edit_competence(Competence competence);
	void dell_competence(Long oid);
	List<Competence> list_competence();
	Competence list_one_competence(Long oid);
}
