package com.example.ajc_backend.services.implementations.candidat;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.dao.LoisirRepository;
import com.example.ajc_backend.entites.Loisir;
import com.example.ajc_backend.services.interfaces.candidat.LosirService;

@Service
public class Loisirserviceimplementation implements LosirService{

	@Autowired
	LoisirRepository  loisirRepository; 
	
	@Override
	public Loisir add_loisir(Loisir loisir) {
		// TODO Auto-generated method stub
		return loisirRepository.save(loisir);
	}

	@Override
	public Loisir edit_loisir(Loisir loisir) {
		// TODO Auto-generated method stub
		Optional<Loisir> loisir2 = loisirRepository.findById(loisir.getOid());
		loisir2.get().setDetail(loisir.getDetail());
		loisir2.get().setLibelle(loisir.getLibelle());
		return loisirRepository.save(loisir2.get());
	}

	@Override
	public void dell_loisir(Long oid) {
		// TODO Auto-generated method stub
		loisirRepository.deleteById(oid);
	}

	@Override
	public List<Loisir> list_loisir() {
		// TODO Auto-generated method stub
		return loisirRepository.findAll();
	}

	@Override
	public Loisir list_one_loisir(Long oid) {
		// TODO Auto-generated method stub
		Optional<Loisir> loisir = loisirRepository.findById(oid);
		return loisir.get();
	}

}
