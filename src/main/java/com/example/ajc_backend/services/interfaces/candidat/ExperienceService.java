package com.example.ajc_backend.services.interfaces.candidat;

import java.util.List;

import com.example.ajc_backend.entites.Experiencepro;

public interface ExperienceService {

	Experiencepro add_experience(Experiencepro experiencepro);
	Experiencepro edit_experience(Experiencepro experiencepro);
	void dell_experience(Long oid);
	List<Experiencepro> list_experience();
	Experiencepro list_one_experience(Long oid);
}
