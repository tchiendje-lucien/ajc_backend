package com.example.ajc_backend.services.implementations.candidat;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.dao.CandidatRepository;
import com.example.ajc_backend.entites.Candidat;
import com.example.ajc_backend.services.interfaces.candidat.CandidatService;

@Service
public class Candidatserviceimplementation implements CandidatService{
    
	@Autowired
	CandidatRepository candidatRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Candidat add_candidat(Candidat candidat) {
		// TODO Auto-generated method stub
		Candidat candidat2=new Candidat();
		candidat2.setCivilite(candidat.getCivilite());
		candidat2.setEmail(candidat.getEmail());
		candidat2.setNom(candidat.getNom());
		candidat2.setPrenom(candidat.getPrenom());
		candidat2.setPassword(bCryptPasswordEncoder.encode(candidat.getPassword()));
		return candidatRepository.save(candidat2);
	}

	@Override
	public Candidat edit_candidat(Candidat candidat) {
		// TODO Auto-generated method stub
		Optional<Candidat>  candidat2 = candidatRepository.findById(candidat.getOid());
		candidat2.get().setCivilite(candidat.getCivilite());
		candidat2.get().setEmail(candidat.getEmail());
		candidat2.get().setNom(candidat.getNom());
		candidat2.get().setPassword(bCryptPasswordEncoder.encode(candidat.getPassword()));
		return candidatRepository.save(candidat2.get());
	}

	@Override
	public void dell_candidat(Long oid) {
		// TODO Auto-generated method stub
		candidatRepository.deleteById(oid);
	}

	@Override
	public List<Candidat> list_candidat() {
		// TODO Auto-generated method stub
		return candidatRepository.findAll();
	}

	@Override
	public Candidat list_one_candidat(Long oid) {
		// TODO Auto-generated method stub
		Optional<Candidat> candidat = candidatRepository.findById(oid);
		return candidat.get();
	}

	@Override
	public Candidat connexion(String user, String pwd) {
		// TODO Auto-generated method stub	 
		Optional<Candidat> candidat = candidatRepository.findByUsernameAndPwd(user,pwd);
		return candidat.get();
	}

	@Override
	public Candidat loadByUserName(String username) {
		// TODO Auto-generated method stub
		return candidatRepository.findByUsername(username);
	}

}
