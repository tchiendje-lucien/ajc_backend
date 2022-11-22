package com.example.ajc_backend.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.entites.Candidat;
import com.example.ajc_backend.services.interfaces.candidat.CandidatService;


@Service
public class UserDetailServiceImp implements UserDetailsService{

	@Autowired
	private CandidatService candidatService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Candidat candidat=candidatService.loadByUserName(username);
		if(candidat==null) throw new UsernameNotFoundException("invalid user");
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(candidat.getRole()));
		
		return new User(candidat.getEmail(),candidat.getPassword(),authorities);
	}

}
