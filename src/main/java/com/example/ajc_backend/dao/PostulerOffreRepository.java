package com.example.ajc_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.ajc_backend.entites.PostulerOffre;


@RepositoryRestResource
public interface PostulerOffreRepository extends JpaRepository<PostulerOffre, Long>{

}
