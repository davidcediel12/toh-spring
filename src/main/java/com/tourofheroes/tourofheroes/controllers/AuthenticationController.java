package com.tourofheroes.tourofheroes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourofheroes.tourofheroes.DTOs.AuthenticationRequest;
import com.tourofheroes.tourofheroes.DTOs.AuthenticationResponse;
import com.tourofheroes.tourofheroes.DTOs.UserDTO;
import com.tourofheroes.tourofheroes.securtiry.JWTUtil;
import com.tourofheroes.tourofheroes.services.AuthUsersService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	
	
	// Service from spring
	@Autowired
	private AuthenticationManager authManager;
	
	// Service to get the users
	@Autowired
	private AuthUsersService userDetailService;
	
	// Service to manage the JWT (decode, build, get claims)
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest auth) {
//		System.out.println("Im the controller");
		try {
			/*
			 * Si le enviamos unas credenciales que estan malas, arrojara el 
			 * error
			 */
			System.out.println(auth.getPassword());
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(auth.getUsername(), 
							auth.getPassword()));
			/*
			 * Obtiene los detalles del usuario
			 */
			UserDetails userDetails = userDetailService.loadUserByUsername(
					auth.getUsername());
			// Crea y envia el JWT
			String jwt = jwtUtil.generateToken(userDetails);
			return ResponseEntity.ok(new AuthenticationResponse(jwt));
			
			
		}catch (BadCredentialsException e) {
			/*
			 * Si se otorgaron unas credenciales erroneas, que arroje un 403
			 */
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}	
	}
	
	
	@PostMapping("/newUser")
	public ResponseEntity<UserDTO> newUser(@RequestBody UserDTO userDto){
		System.out.println("HEELLO " + userDto.getName());
		System.out.println(userDto.toString());
		if(userDetailService.newUser(userDto))
			return ResponseEntity.ok(userDto);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
