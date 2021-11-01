package com.tourofheroes.tourofheroes.services;


import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tourofheroes.tourofheroes.DTOs.UserDTO;
import com.tourofheroes.tourofheroes.model.MyUser;
import com.tourofheroes.tourofheroes.repositories.UserRepository;

@Service
public class AuthUsersService implements UserDetailsService {
	
	
	@Autowired 
	UserRepository userRepo;
	@Autowired
	ModelMapper mapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		 *  Create a specific user using spring security, 
		 *  User(name, password, errors)
		 *  We use {noop} because the password is not encrypted yet
		 */
		UserDTO userDto = findById(username);
		if(userDto == null)
			throw new UsernameNotFoundException("User not found");
		
		
		return new User(userDto.getUsername(), userDto.getPassword(), new ArrayList<>());
	}
	
	
	public boolean newUser(UserDTO userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		try {
			userRepo.save(mapper.map(userDto, MyUser.class));
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public UserDTO findById(String username) {
		Optional<MyUser> userOpt = userRepo.findById(username);
		if(userOpt.isEmpty())
			return null;
		return mapper.map(userOpt.get(), UserDTO.class);
	}

}
