package com.example.ajc_backend.dao;

import com.example.ajc_backend.entites.ActivitySectors;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitySectorsRepository extends JpaRepository<ActivitySectors, Long> {
    Optional<ActivitySectors> findByName(String name);
}