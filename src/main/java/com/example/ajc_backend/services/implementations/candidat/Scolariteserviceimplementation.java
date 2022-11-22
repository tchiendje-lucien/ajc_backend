package com.example.ajc_backend.services.implementations.candidat;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.dao.ScolariteRepository;
import com.example.ajc_backend.entites.Scolarite;
import com.example.ajc_backend.services.interfaces.candidat.ScolariteService;

@Service
public class Scolariteserviceimplementation implements ScolariteService{
    @Autowired
	ScolariteRepository scolariteRepository;
	
	@Override
	public Scolarite add_scolarite(Scolarite scolarite) {
		// TODO Auto-generated method stub
		return scolariteRepository.save(scolarite);
	}

	@Override
	public Scolarite edit_scolarite(Scolarite scolarite) {
		// TODO Auto-generated method stub
		Optional<Scolarite> scolarite2 = scolariteRepository.findById(scolarite.getOid());
		scolarite2.get().setDatedebut(scolarite.getDatedebut());
		scolarite2.get().setDatefin(scolarite.getDatefin());
		scolarite2.get().setDomaine(scolarite.getDomaine());
		scolarite2.get().setNiveau(scolarite.getNiveau());
		scolarite2.get().setPays(scolarite.getPays());
		scolarite2.get().setVille(scolarite.getVille());
		
		return scolariteRepository.save(scolarite2.get());
	}

	@Override
	public void dell_scolarite(Long oid) {
		// TODO Auto-generated method stub
		scolariteRepository.deleteById(oid);
	}

	@Override
	public List<Scolarite> list_scolarite() {
		// TODO Auto-generated method stub
		return scolariteRepository.findAll();
	}

	@Override
	public Scolarite list_one_scolarite(Long oid) {
		// TODO Auto-generated method stub
		Optional<Scolarite> scolarite = scolariteRepository.findById(oid);
		return scolarite.get();
	}

}
