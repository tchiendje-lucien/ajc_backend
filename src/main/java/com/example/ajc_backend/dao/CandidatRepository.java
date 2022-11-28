package com.example.ajc_backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ajc_backend.entites.Candidat;

@RepositoryRestResource
public interface CandidatRepository extends JpaRepository<Candidat, Long>{

	@Query(value="select c from Candidat c where c.email=?1 and c.password=?2")
	Optional<Candidat> findByUsernameAndPwd(String email, String pwd);
	
	@Query(value="select c from Candidat c where c.email=?1")
	Candidat findByUsername(String email);
}
