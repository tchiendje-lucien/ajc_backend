package com.example.ajc_backend.services.interfaces.candidat;

import java.util.List;

import com.example.ajc_backend.entites.Langue;

public interface LangueService {

	Langue add_langue(Langue langue);
	Langue edit_langue(Langue langue);
	void dell_langue(Long oid);
	List<Langue> list_langue();
	Langue list_one_langue(Long oid);
}
