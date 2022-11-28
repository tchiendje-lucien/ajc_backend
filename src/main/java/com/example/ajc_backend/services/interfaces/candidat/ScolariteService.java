package com.example.ajc_backend.services.interfaces.candidat;

import java.util.List;

import com.example.ajc_backend.entites.Scolarite;

public interface ScolariteService {

	Scolarite add_scolarite(Scolarite scolarite);
	Scolarite edit_scolarite(Scolarite scolarite);
	void dell_scolarite(Long oid);
	List<Scolarite> list_scolarite();
	Scolarite list_one_scolarite(Long oid);
}
