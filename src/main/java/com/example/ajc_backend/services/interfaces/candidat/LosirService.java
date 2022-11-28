package com.example.ajc_backend.services.interfaces.candidat;

import java.util.List;

import com.example.ajc_backend.entites.Loisir;

public interface LosirService {

	Loisir add_loisir(Loisir loisir);
	Loisir edit_loisir(Loisir loisir);
	void dell_loisir(Long oid);
	List<Loisir> list_loisir();
	Loisir list_one_loisir(Long oid);
}
