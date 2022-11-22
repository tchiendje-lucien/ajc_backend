package com.example.ajc_backend.dao;

import com.example.ajc_backend.entites.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaysRepository extends JpaRepository<Pays, Long> {
}