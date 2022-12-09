package com.example.ajc_backend.dao;

import com.example.ajc_backend.entites.EntrepriseAccount;


import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseAccountRepository extends JpaRepository<EntrepriseAccount, Long> {
    // Optional<EntrepriseAccount> findBy
}