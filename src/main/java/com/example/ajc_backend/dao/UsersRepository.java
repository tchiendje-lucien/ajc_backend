package com.example.ajc_backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ajc_backend.entites.Users;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByIdentifiant(String identifiant);

    @Query(value="select u from Users u where u.identifiant=?1")
	Users findByUsername(String identifiant);
}
