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
import com.tourofheroes.tourofheroes.securtiry.JWTUtil;
import com.tourofheroes.tourofheroes.services.AuthUsersService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
	
	
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private AuthUsersService userDetailService;
	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest auth) {
		System.out.println("Im the controller");
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(auth.getUsername(), 
							auth.getPassword()));

			UserDetails userDetails = userDetailService.loadUserByUsername(
					auth.getUsername());
			
			String jwt = jwtUtil.generateToken(userDetails);
			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		}catch (BadCredentialsException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		
	}
}
