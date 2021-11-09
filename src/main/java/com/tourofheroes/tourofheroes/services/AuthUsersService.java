package com.tourofheroes.tourofheroes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tourofheroes.tourofheroes.DTOs.UserDTO;
import com.tourofheroes.tourofheroes.model.MyUser;
import com.tourofheroes.tourofheroes.model.UserRole;
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
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		 *  Create a specific user using spring security, 
		 *  User(name, password, errors)
		 *  We use {noop} because the password is not encrypted yet
		 */
		MyUser user = userRepo.findById(username).orElse(null);
		if(user == null)
			throw new UsernameNotFoundException("User not found " + username);
		
		System.out.println("OBTAINING ROLES for " + username);
		List<UserRole> rawRoles = user.getRoles();
		System.out.println("Found " + rawRoles.size() + " roles");
		// Obtaining the roles
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		for(UserRole role : rawRoles) {
			System.out.println("rol " + role.getName());
			roles.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		
		// TO DO: Add user roles
		return User.withUsername(user.getUsername())
				.password(user.getPassword())
				.authorities(roles)
				.build();
	}
	
	
	public boolean newUser(UserDTO userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		if(userRepo.existsById(userDto.getUsername()))
			return false;
		// Using Lombok builder 
		userRepo.save(MyUser.builder()
			.username(userDto.getUsername())
			.password(userDto.getPassword())
			.build()
			);
		return true;
	}
	
	public UserDTO findById(String username) {
		Optional<MyUser> userOpt = userRepo.findById(username);
		if(userOpt.isEmpty())
			return null;
		return mapper.map(userOpt.get(), UserDTO.class);
	}

}
