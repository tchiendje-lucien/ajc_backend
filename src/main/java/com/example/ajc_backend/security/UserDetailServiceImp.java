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
import com.example.ajc_backend.entites.Users;
import com.example.ajc_backend.services.interfaces.UserService;
import com.example.ajc_backend.services.interfaces.candidat.CandidatService;


@Service
public class UserDetailServiceImp implements UserDetailsService{

	// @Autowired
	// private CandidatService candidatService;
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user=userService.loadByUserName(username);
		if(user==null) throw new UsernameNotFoundException("invalid user");
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		
		return new User(user.getIdentifiant(),user.getPassword(),authorities);
	}

}
