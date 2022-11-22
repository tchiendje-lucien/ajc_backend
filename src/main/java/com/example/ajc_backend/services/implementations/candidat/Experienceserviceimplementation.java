package com.example.ajc_backend.services.implementations.candidat;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.dao.ExperienceRepository;
import com.example.ajc_backend.entites.Experiencepro;
import com.example.ajc_backend.services.interfaces.candidat.ExperienceService;

@Service
public class Experienceserviceimplementation implements ExperienceService{

	@Autowired
	ExperienceRepository experiencerepository;
	
	@Override
	public Experiencepro add_experience(Experiencepro experiencepro) {
		// TODO Auto-generated method stub
		return experiencerepository.save(experiencepro);
	}

	@Override
	public Experiencepro edit_experience(Experiencepro experiencepro) {
		// TODO Auto-generated method stub
		Optional<Experiencepro> experiencepro2 = experiencerepository.findById(experiencepro.getOid());
		experiencepro2.get().setDatedebut(experiencepro.getDatedebut());
		experiencepro2.get().setDatefin(experiencepro.getDatefin());
		experiencepro2.get().setEntreprise(experiencepro.getEntreprise());
		experiencepro2.get().setOccupation(experiencepro.getOccupation());
		experiencepro2.get().setPoste(experiencepro.getPoste());
		experiencepro2.get().setTache(experiencepro.getTache());
		return experiencerepository.save(experiencepro2.get());
	}

	@Override
	public void dell_experience(Long oid) {
		// TODO Auto-generated method stub
		experiencerepository.deleteById(oid);
	}

	@Override
	public List<Experiencepro> list_experience() {
		// TODO Auto-generated method stub
		return experiencerepository.findAll();
	}

	@Override
	public Experiencepro list_one_experience(Long oid) {
		// TODO Auto-generated method stub
		Optional<Experiencepro> experiencepro = experiencerepository.findById(oid);
		return experiencepro.get();
	}

}
