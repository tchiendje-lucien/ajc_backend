package com.example.ajc_backend.services.implementations.candidat;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.ExceptionMessage;
import com.example.ajc_backend.dao.CandidatRepository;
import com.example.ajc_backend.dao.UsersRepository;
import com.example.ajc_backend.entites.Candidat;
import com.example.ajc_backend.entites.Users;
import com.example.ajc_backend.services.interfaces.candidat.CandidatService;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
public class Candidatserviceimplementation implements CandidatService {
    
	@Autowired
	CandidatRepository candidatRepository;
	@Autowired
	UsersRepository usersRepositry;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Candidat add_candidat(Candidat candidat) throws ExceptionMessage{
		// TODO Auto-generated method stub
		
		Optional<Candidat>  candidatr = candidatRepository.findByUsername(candidat.getEmail());
		if(candidatr.isPresent()) {			
			System.out.print("Message");
           throw new ExceptionMessage("Un utilisateur existe déja avec cet email");
		}else {
			
			 Users users = new Users();
		     users.setIdentifiant(candidat.getEmail());
		     users.setPassword(bCryptPasswordEncoder.encode(candidat.getPassword()));
		     users.setRe_password(bCryptPasswordEncoder.encode(candidat.getPassword()));
		     users.setRole("CANDIDAT");
		     users = usersRepositry.save(users);
		     
			Candidat candidat2=new Candidat();
			candidat2.setCivilite(candidat.getCivilite());
			candidat2.setEmail(candidat.getEmail());
			candidat2.setNom(candidat.getNom());
			candidat2.setPrenom(candidat.getPrenom());
			candidat2.setPassword(bCryptPasswordEncoder.encode(candidat.getPassword()));
			candidat2.setUsers(users);
			candidat2.setRole("CANDIDAT");	
			
		     
			 return candidatRepository.save(candidat2);
		}
	
	}

	@Override
	public Candidat edit_candidat(Candidat candidat) {
		// TODO Auto-generated method stub
		Optional<Candidat>  candidat2 = candidatRepository.findById(candidat.getOid());
		candidat2.get().setCivilite(candidat.getCivilite());
		candidat2.get().setEmail(candidat.getEmail());
		candidat2.get().setNom(candidat.getNom());
		candidat2.get().setPrenom(candidat.getPrenom());
		//candidat2.get().setPassword(bCryptPasswordEncoder.encode(candidat.getPassword()));
		candidat2.get().setDatenaissance(candidat.getDatenaissance());
		candidat2.get().setLieumaissance(candidat.getLieumaissance());
		candidat2.get().setPays(candidat.getPays());
		candidat2.get().setVille(candidat.getVille());
		candidat2.get().setTelephone1(candidat.getTelephone1());
		candidat2.get().setTelephone2(candidat.getTelephone2());
		candidat2.get().setNbreanneeexp(candidat.getNbreanneeexp());
		candidat2.get().setFonction(candidat.getFonction());
		candidat2.get().setObjectif(candidat.getObjectif());
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
		Optional<Candidat> candidat = candidatRepository.findByUsername(username);
		return candidat.get();
	}

	@Override
	public Users tokenUser(String jwtToken) {
		Gson g = new Gson();
        jwtToken = jwtToken.replace("Bearer ", "");
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        User user = g.fromJson(body, User.class);
        // System.out.println("JWT Body : " + user.getSub());
        Users users = usersRepositry.findByUsername(user.getSub());
        		//candidatRepository.findByUsername(user.getSub());
        return users;
	}

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {

    private String sub;
    private String[] roles;
    private String iss;
    private String exp;

}
