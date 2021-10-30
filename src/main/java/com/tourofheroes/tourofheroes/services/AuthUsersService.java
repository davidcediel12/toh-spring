package com.tourofheroes.tourofheroes.services;


import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUsersService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		 *  Create a specific user using spring security, 
		 *  User(name, password, errors)
		 *  We use {noop} because the password is not encrypted yet
		 */
		return new User("1088350146", "{noop}hola123", new ArrayList<>());
	}

}
