package com.example.ajc_backend.services.implementations.candidat;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.dao.LangueRepository;
import com.example.ajc_backend.entites.Langue;
import com.example.ajc_backend.services.interfaces.candidat.LangueService;

@Service
public class Langueserviceimplementation implements LangueService{

	@Autowired
	LangueRepository langueRepository;
	
	@Override
	public Langue add_langue(Langue langue) {
		// TODO Auto-generated method stub
		return langueRepository.save(langue);
	}

	@Override
	public Langue edit_langue(Langue langue) {
		// TODO Auto-generated method stub
		Optional<Langue> langue2 = langueRepository.findById(langue.getOid());
		langue2.get().setLangue(langue.getLangue());
		langue2.get().setEcrit(langue.getEcrit());
		langue2.get().setLue(langue.getLue());
		langue2.get().setParle(langue.getParle());
		return langueRepository.save(langue2.get());
	}

	@Override
	public void dell_langue(Long oid) {
		// TODO Auto-generated method stub
		langueRepository.deleteById(oid);
	}

	@Override
	public List<Langue> list_langue() {
		// TODO Auto-generated method stub
		return langueRepository.findAll();
	}

	@Override
	public Langue list_one_langue(Long oid) {
		// TODO Auto-generated method stub
		Optional<Langue> langue = langueRepository.findById(oid);
		return langue.get();
	}

}
