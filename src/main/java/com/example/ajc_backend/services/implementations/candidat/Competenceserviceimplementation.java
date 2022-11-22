package com.example.ajc_backend.services.implementations.candidat;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.dao.CompetenceRepository;
import com.example.ajc_backend.entites.Competence;
import com.example.ajc_backend.services.interfaces.candidat.CompetenceService;

@Service
public class Competenceserviceimplementation implements CompetenceService{

	@Autowired
	CompetenceRepository competenceRepository;
	
	@Override
	public Competence add_competence(Competence competence) {
		// TODO Auto-generated method stub
		return competenceRepository.save(competence);
	}

	@Override
	public Competence edit_competence(Competence competence) {
		// TODO Auto-generated method stub
		Optional<Competence> competence2 = competenceRepository.findById(competence.getOid());
		competence2.get().setDetail(competence.getDetail());
		competence2.get().setDomaine(competence.getDomaine());
		competence2.get().setCandidat(competence.getCandidat());
		return competenceRepository.save(competence2.get());
	}

	@Override
	public void dell_competence(Long oid) {
		// TODO Auto-generated method stub
		competenceRepository.deleteById(oid);
	}

	@Override
	public List<Competence> list_competence() {
		// TODO Auto-generated method stub
		return competenceRepository.findAll();
	}

	@Override
	public Competence list_one_competence(Long oid) {
		// TODO Auto-generated method stub
		Optional<Competence> competence = competenceRepository.findById(oid);
 		return competence.get();
	}

}
